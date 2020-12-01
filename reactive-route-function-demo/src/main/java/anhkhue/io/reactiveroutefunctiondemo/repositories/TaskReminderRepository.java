package anhkhue.io.reactiveroutefunctiondemo.repositories;

import anhkhue.io.reactiveroutefunctiondemo.model.TaskReminder;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TaskReminderRepository extends ReactiveCrudRepository<TaskReminder, String> {

}
