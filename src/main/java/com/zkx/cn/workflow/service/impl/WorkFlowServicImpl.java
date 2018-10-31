package com.zkx.cn.workflow.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkx.cn.domain.BPMNParam;
import com.zkx.cn.personel.mapper.EmployeeMapper;
import com.zkx.cn.util.MapUtil;
import com.zkx.cn.workflow.service.WorkFlowService;

/**
 * 流程控制接口实现类
 * @author 张凯旋
 *
 */
@Service
public class WorkFlowServicImpl implements WorkFlowService {

	@Autowired  
    private RuntimeService runtimeService;  
    @Autowired  
    private TaskService taskService;  
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private EmployeeMapper employeeMapper;
    
    
	@Override
	public String startProcess(BPMNParam param) {
		String processId = null;
		String key = param.getProcessKey();
		Map<String, Object> map;//流程参数
		
		try {
			//根据流程Key启动流程并通过流程参数设置首节点执行人
			map = MapUtil.objectToMap(param);
			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, map);
			//获取该次流程的唯一流程参数
			processId = processInstance.getProcessDefinitionId();
			//执行流程一次进入第一节点
			performTask (param);
		} catch (Exception e) {
			e.printStackTrace();
			processId = null;
		}
		
		return processId;
	}
	
	@Override
	public void performTask (BPMNParam param) {
		String taskId;
		String userId = param.getRole();
		userId = employeeMapper.getLeader(userId);//获取上级的userId，将下一节点执行人设置为上级
		param.setRole(userId);
		Map<String, Object> map;//流程参数
		try {
			map = MapUtil.objectToMap(param);
			taskId = getTaskId(param);
			//获取任务ID执行该任务，并通过设置流程参数控制流程走向
			taskService.complete(taskId, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void workFlowTest() {
        System.out.println("method startActivityDemo begin....");  
        
        System.out.println( "调用流程存储服务，查询部署数量："
                + repositoryService.createDeploymentQuery().count());
      
        Map<String,Object> map = new HashMap<String,Object>();  
        map.put("role","zhangsan");  
        
        //流程启动  
        runtimeService.startProcessInstanceByKey("myProcess",map);  
       
        List<Task> tq=taskService.createTaskQuery().taskAssignee("zhangsan").list();
        System.out.println(tq.size());
        String assignee = "zhangsan";//当前任务办理人  
        List<Task> tasks = taskService//与任务相关的Service  
                .createTaskQuery()//创建一个任务查询对象  
                .taskAssignee(assignee)  
                .list();  
        if(tasks !=null && tasks.size()>0){  
            for(Task task:tasks){  
                System.out.println("任务ID:"+task.getId());  
                System.out.println("任务的办理人:"+task.getAssignee());  
                System.out.println("任务名称:"+task.getName());  
                System.out.println("任务的创建时间:"+task.getCreateTime());  
 
                System.out.println("流程实例ID:"+task.getProcessInstanceId());
                System.out.println("#####################################");  
            }  
        }  
     
        System.out.println("method startActivityDemo end....");  
	}
	
	@Override
	public String getTaskId(BPMNParam param) {
		String processId = param.getProcessId();
		String userId = param.getRole();
		//获取当前流程当前任务
		TaskQuery taskQuery = taskService.createTaskQuery()//获取任务集
				.processDefinitionId(processId);//筛选条件：流程id
		
		if (userId != null) {
			taskQuery = taskQuery.taskAssignee(userId);//筛选条件任务：执行人
		}
		
		Task task = taskQuery.singleResult();
		return task.getId();
	}

}
