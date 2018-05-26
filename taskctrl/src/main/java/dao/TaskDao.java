package dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import entity.Task;

@Transactional
public interface TaskDao extends CrudRepository<Task, Long> {
	
	public List<Task> findByStatus(String status);

}
