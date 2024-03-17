package com.jsp.springboot.actor.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.springboot.actor.model.Actor;
import com.jsp.springboot.actor.utility.ResponseStructure;

public interface ActorService {

	ResponseEntity<ResponseStructure<Actor>> addActor(Actor actor);

	ResponseEntity<ResponseStructure<Actor>> findByActorId(int actorId);

	ResponseEntity<ResponseStructure<Actor>> updateByActorId(int actorId,Actor updatedActor);

	ResponseEntity<ResponseStructure<Actor>> deleteByActorId(int actorId);

	ResponseEntity<ResponseStructure<List<Actor>>> findAllActors();


}
