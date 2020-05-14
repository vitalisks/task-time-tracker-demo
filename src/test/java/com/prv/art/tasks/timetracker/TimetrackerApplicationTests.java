package com.prv.art.tasks.timetracker;

import com.prv.art.tasks.timetracker.services.TaskValidationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TimetrackerApplicationTests {

	@Autowired
	private TaskValidationService validationService;

	@Test
	void test_taskValidationScenario(){
		/*
		TaskDto pt=new TaskDto();
		//pt.setFinished(true);
		pt.setTaskName("Task1");
		pt.setTimeSpent(10);
		pt.setSubTasks(new ArrayList<TaskDto>());

		TaskDto t=new TaskDto();
		t.setTaskName("Subtask 1");
		t.setFinished(true);
		t.setTimeSpent(10);
		pt.getSubTasks().add(t);

		t=new TaskDto();
		t.setTaskName("Subtask 1");
		t.setTimeSpent(10);
		pt.getSubTasks().add(t);

		t=new TaskDto();
		t.setTaskName("Subtask 2");
		pt.getSubTasks().add(t);
		boolean r =validationService.allowTaskCompletion(pt);
		assertEquals(1,1);*/
	}
//	@Test
	//void contextLoads() {
	//}
/*
	@Test
	void testMapping(){
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration()
				//.setFieldMatchingEnabled(true)
				//.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
		;


		modelMapper.addConverter(new Converter<List<Task>, List<TaskDto>>() {
			@Override
			public List<TaskDto> convert(MappingContext<List<Task>, List<TaskDto>> context) {
				if (context.getSource()!=null){
					return context.getSource().stream().map(t->modelMapper.map(t,TaskDto.class)).collect(Collectors.toList());
				}else{
					return new ArrayList<TaskDto>();
				}

			}
		});
		Task pt=new Task();
		pt.setTaskName("Task1");
		pt.setSubTasks(new ArrayList<Task>());

		Task t=new Task();
		t.setTaskName("Subtask 1");
		pt.getSubTasks().add(t);

		t=new Task();
		t.setTaskName("Subtask 2");
		pt.getSubTasks().add(t);

		TaskDto td=modelMapper.map(pt, TaskDto.class);
		assertEquals(td.getId(),0);
	}*/

}
