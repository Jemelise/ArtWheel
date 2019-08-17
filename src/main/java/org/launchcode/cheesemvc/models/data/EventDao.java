package org.launchcode.cheesemvc.models.data;

import org.launchcode.cheesemvc.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface EventDao extends CrudRepository<Event, Integer> {
}
