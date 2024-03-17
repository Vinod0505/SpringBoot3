package com.jsp.springboot.actor.serviceImpl;

import java.util.List;
import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.springboot.actor.exception.ActorNotFoundByIdException;
import com.jsp.springboot.actor.exception.ActorsNotFoundException;
import com.jsp.springboot.actor.model.Actor;
import com.jsp.springboot.actor.repository.ActorRepository;
import com.jsp.springboot.actor.service.ActorService;
import com.jsp.springboot.actor.utility.ResponseStructure;

@Service
public class ActorServiceImpl implements ActorService {

	@Autowired
	private ActorRepository actorRepository;


	@Override
	public ResponseEntity<ResponseStructure<Actor>> addActor(Actor actor) {
		Actor actor2 = actorRepository.save(actor);
		ResponseStructure<Actor> responseStructure = new ResponseStructure<>();
		responseStructure.setStatuscode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Actor Object Created Successfully");
		responseStructure.setData(actor2);

		return new ResponseEntity<ResponseStructure<Actor>>(responseStructure,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<Actor>> findByActorId(int actorId) {
		Optional<Actor> optional = actorRepository.findById(actorId);
		if(optional.isPresent()) {
			Actor actor = optional.get();
			ResponseStructure<Actor> responseStructure = new ResponseStructure<>();
			responseStructure.setStatuscode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Actor Object Found");
			responseStructure.setData(actor);
			return new ResponseEntity<ResponseStructure<Actor>>(responseStructure, HttpStatus.FOUND);
		}else {
			throw new ActorNotFoundByIdException("Actor Not Found  ");
		}

	}

	@Override
	public ResponseEntity<ResponseStructure<Actor>> updateByActorId(int actorId, Actor updatedActor) {
		Optional<Actor> optional = actorRepository.findById(actorId);
		if(optional.isPresent()) {
			Actor existingActor = optional.get();
			updatedActor.setActorId(existingActor.getActorId());  //updatedActor.setActorId(actorId);
			actorRepository.save(updatedActor);
			ResponseStructure<Actor> responseStructure = new ResponseStructure<>();
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("Actor Object Updated successfully ");
			responseStructure.setData(existingActor);

			return new ResponseEntity<ResponseStructure<Actor>>(responseStructure,HttpStatus.OK);
		}else
			throw new ActorNotFoundByIdException("Actor Not Found");
	}

	@Override
	public ResponseEntity<ResponseStructure<Actor>> deleteByActorId(int actorId) {
		Optional<Actor> optional = actorRepository.findById(actorId);
		if(optional.isPresent()) {
			Actor actor = optional.get();
			actorRepository.delete(actor);//deleteById(actorid);

			ResponseStructure<Actor> responseStructure = new ResponseStructure<>();
			responseStructure.setStatuscode(HttpStatus.OK.value());
			responseStructure.setMessage("Actor Object Updated successfully ");
			responseStructure.setData(actor);

			return new ResponseEntity<ResponseStructure<Actor>>(responseStructure, HttpStatus.OK);
		}else {
			throw new ActorNotFoundByIdException("Actor Not Found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Actor>>> findAllActors() {

		List<Actor> actors = actorRepository.findAll();
		
		ResponseStructure<List<Actor>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatuscode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Actors Found");
		responseStructure.setData(actors);

		if(actors.isEmpty()) {
			throw new ActorsNotFoundException("Actors Not Found !!");
		}else

			return new ResponseEntity<ResponseStructure<List<Actor>>>(responseStructure, HttpStatus.FOUND);
	}


}
