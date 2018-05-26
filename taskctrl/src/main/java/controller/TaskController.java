package controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import bean.TaskBean;
import dao.TaskDao;
import entity.Task;

@RestController
public class TaskController {
	
	private final String STATUS_CRIACAO = "Em aberto";
	private final String STATUS_CONCLUIDO = "Concluída";
	private final String STATUS_EDITADA = "Editada";
	private final String STATUS_REMOVIDA = "Removida";
	

	@Autowired
	private TaskDao taskDao;
	
	public TaskController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value="/tasks/criar", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> criarTask(@RequestBody String conteudo) {
		String taskId = "";
		try {
			Gson gson = new Gson();
			TaskBean taskBean = gson.fromJson(conteudo, TaskBean.class);
			Task task = new Task();
			task.setTitulo(taskBean.getTitulo());
			task.setDescricao(taskBean.getDescricao());
			task.setStatus(STATUS_CRIACAO);
			task.setDataCriacao(new Date());
			taskDao.save(task);
			taskId = String.valueOf(task.getId());
			System.out.println(conteudo);
		} catch (Exception e) {
			return new ResponseEntity<String>("-1", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(taskId, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/tasks/listar-todas", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> findAllTasks() {
	    List<Task> lista = (List<Task>) taskDao.findAll();
	    return new ResponseEntity<List<Task>>(lista, HttpStatus.OK);
	}
	

	@RequestMapping(value="/tasks/remover", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> delete(@RequestBody String conteudo) {
		try {
			Gson gson = new Gson();
			TaskBean taskBean = gson.fromJson(conteudo, TaskBean.class);
			Task task = new Task();
			task.setId(taskBean.getId());
			task = taskDao.findOne(task.getId());
			task.setDataRemocao(new Date());
			task.setStatus(STATUS_REMOVIDA);
			taskDao.save(task);
			System.out.println(conteudo);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao remover task", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Task removida com sucesso", HttpStatus.OK);
	}
	

	@RequestMapping(value="/tasks/concluir", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> concluir(@RequestBody String conteudo) {
		try {
			Gson gson = new Gson();
			TaskBean taskBean = gson.fromJson(conteudo, TaskBean.class);
			Task task = new Task();
			task.setId(taskBean.getId());
			task = taskDao.findOne(task.getId());
			
			if (taskBean.getStatus().equals(STATUS_CRIACAO)) {
				task.setDataConclusao(null);
				task.setStatus(STATUS_CRIACAO);
			}
			else if (taskBean.getStatus().equals(STATUS_CONCLUIDO)) {
				task.setDataConclusao(new Date());
				task.setStatus(STATUS_CONCLUIDO);
			}
			
			taskDao.save(task);
			System.out.println(conteudo);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao remover task", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Task removida com sucesso", HttpStatus.OK);
	}
	

	@RequestMapping(value="/tasks/editar", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> editar(@RequestBody String conteudo) {
		try {
			Gson gson = new Gson();
			TaskBean taskBean = gson.fromJson(conteudo, TaskBean.class);
			Task task = new Task();
			task.setId(taskBean.getId());
			task = taskDao.findOne(task.getId());
			task.setTitulo(taskBean.getTitulo());
			task.setDescricao(taskBean.getDescricao());
			task.setDataEdicao(new Date());
			task.setStatus(STATUS_EDITADA);
			taskDao.save(task);
			System.out.println(conteudo);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao editar task", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Task editada com sucesso", HttpStatus.OK);
	}
	/*
	public String editar(long id, String titulo, String descricao) {
		try {
			Task task = new Task();
			task.setId(id);
			task = taskDao.findOne(task.getId());
			task.setTitulo(titulo);
			task.setDescricao(descricao);
			task.setDataEdicao(new Date());
			task.setStatus(STATUS_EDITADA);
			taskDao.save(task);
		} catch (Exception ex) {
			return "Erro ao editar a task:" + ex.toString();
		}
		return "Task editada com sucesso!";
	}
	*/

}
