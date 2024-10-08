package com.aurionpro.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.aurionpro.batch.entity.Employee;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class EmployeeBatchConfig {

//	@Bean
//	public FlatFileItemReader<Employee> readEmployeeCsv() {
//		FlatFileItemReader<Employee> employeeCsvReader = new FlatFileItemReader<>();
//		employeeCsvReader.setResource(new ClassPathResource("data.csv"));
//		employeeCsvReader.setName("employeeCsvReader");
//		employeeCsvReader.setLinesToSkip(1);
//		employeeCsvReader.setLineMapper(lineMapper());
//		return employeeCsvReader;
//	}

//	private LineMapper<Employee> lineMapper() {
//		DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<>();
//		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
//		lineTokenizer.setDelimiter(",");
//		lineTokenizer.setStrict(false);
//		lineTokenizer.setNames("employeeId", "name", "salary");
//
//		BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
//		fieldSetMapper.setTargetType(Employee.class);
//
//		lineMapper.setLineTokenizer(lineTokenizer);
//		lineMapper.setFieldSetMapper(fieldSetMapper);
//		return lineMapper;
//	}

	@Bean
	public EmployeeProcessor employeeProcessor() {
		return new EmployeeProcessor();
	}

//	@Bean
//	public JdbcBatchItemWriter<Employee> writer(DataSource dataSource) {
//		return new JdbcBatchItemWriterBuilder<Employee>()
//				.sql("INSERT INTO employees (employee_id, name, salary) VALUES (:employeeId, :name, :salary)")
//				.dataSource(dataSource).beanMapped().build();
//	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource data) {
		return new DataSourceTransactionManager(data);
	}

//	@Bean
//	public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
//			FlatFileItemReader<Employee> reader, EmployeeProcessor processor, JdbcBatchItemWriter<Employee> writer) {
//		return new StepBuilder("importcsvstep", jobRepository).<Employee, Employee>chunk(2, transactionManager)
//				.reader(reader).processor(processor).writer(writer).build();
//	}
//	
//	@Bean
//	public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listner) {
//		return new JobBuilder("importUserJob", jobRepository).listener(listner).start(step1).build();
//	}
	
	
	@Bean
	public FlatFileItemWriter<Employee> writer() {
	    FlatFileItemWriter<Employee> writer = new FlatFileItemWriter<>();
	    writer.setResource(new FileSystemResource("output/employees.csv"));
	    writer.setAppendAllowed(false); 
	    writer.setLineAggregator(new DelimitedLineAggregator<Employee>() {
	        {
	            setDelimiter(",");
	            setFieldExtractor(new BeanWrapperFieldExtractor<Employee>() {
	                {
	                    setNames(new String[] { "employeeId", "name", "salary" });
	                }
	            });
	        }
	    });
	    return writer;
	}

	@Bean
	public JpaPagingItemReader<Employee> reader(EntityManagerFactory entityManagerFactory) {
	    JpaPagingItemReader<Employee> reader = new JpaPagingItemReader<>();
	    reader.setEntityManagerFactory(entityManagerFactory);
	    reader.setQueryString("SELECT e FROM Employee e");
	    reader.setPageSize(10); 
	    return reader;
	}

	@Bean
	public Step exportStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
	        JpaPagingItemReader<Employee> reader, EmployeeProcessor processor, FlatFileItemWriter<Employee> writer) {
	    return new StepBuilder("exportStep", jobRepository)
	            .<Employee, Employee>chunk(10, transactionManager)
	            .reader(reader)
	            .processor(processor)
	            .writer(writer)
	            .build();
	}

	@Bean
	public Job exportJob(JobRepository jobRepository, Step exportStep, JobCompletionNotificationListener listener) {
	    return new JobBuilder("exportJob", jobRepository)
	            .listener(listener)
	            .start(exportStep)
	            .build();
	}

}
