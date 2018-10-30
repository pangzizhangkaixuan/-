package com.zkx.cn.workflow.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkx.cn.domain.BPMNParam;
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
    
    private BPMNParam param = BPMNParam.getBPMNParam();
	
	@Override
	public String startProcess() {
		Map<String, Object> map;
		String processID = "";
		try {
			map = MapUtil.objectToMap(param);
			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess",map);
			processID = processInstance.getProcessDefinitionId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return processID;
	}
	
	@Override
	public void performTask (String userId) {
		Map<String, Object> map;
		try {
			map = MapUtil.objectToMap(param);
			//获取任务ID执行该任务，并通过设置流程参数控制流程走向
			taskService.complete(getTaskId(param.getProcessID(), userId), map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	
	/**
	 * 获取任务id
	 * @param processID 流程id一个流程的唯一标识
	 * @param userId 执行人id当前任务执行人的id
	 * @return
	 */
	private String getTaskId(String processID, String userId) {
		//获取当前流程当前任务
		Task task = taskService.createTaskQuery()//获取任务集
				.processDefinitionId(processID)//筛选条件：流程id
				.taskAssignee(userId)//筛选条件任务：执行人
				.singleResult();//获取任务对象
		
		return task.getId();
	}

}
