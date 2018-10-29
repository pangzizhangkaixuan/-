# zero-  
日常练手  

### springBoot集成Demo  
 ### 集成曾经用过的框架  
#### 一、springBoot单个启动  
#### 二、集成mybatis  
#### 三、集成swagger  
#### 四、集成activiti  
##### 1、原有Spring Boot Stater不支持2.0.6版本springBoot，所以更改依赖版本  
    <parent>    
    	<groupId>org.springframework.boot</groupId>   
    	<artifactId>spring-boot-starter-parent</artifactId>     
    	<version>1.5.2.RELEASE</version>  
  	</parent>    
    <dependency>      
	<groupId>org.activiti</groupId>    
	<artifactId>activiti-spring-boot-starter-basic</artifactId>    
	<version>5.21.0</version>    
    </dependency>    
##### 2、不使用Spring Boot Stater，使用配置类集成  
    `<parent>`  
   	`<groupId>org.springframework.boot</groupId>`  
    	`<artifactId>spring-boot-starter-parent</artifactId>`  
    	`<version>2.0.6.RELEASE</version>`  
  	`</parent>`  
    `<dependency>`  
    	`<groupId>org.activiti</groupId>`  
    	`<artifactId>activiti-spring</artifactId>`  
    	`<version>6.0.0</version>`  
    `</dependency>`   
