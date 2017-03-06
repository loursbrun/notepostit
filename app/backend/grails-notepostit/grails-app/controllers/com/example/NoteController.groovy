package com.example

import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class NoteController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def valueEstimateService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Note.list(params), model:[noteCount: Note.count()]
    }

    def show(Note note) {
        respond note
    }


    def create() {
        respond new Note(params)
    }

    @Transactional
    def save(Note note) {
        if (note == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (note.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond note.errors, view:'create'
            return
        }

        note.save flush:true

        request.withFormat {
            form multipartForm {
                //flash.message = message(code: 'default.created.message', args: [message(code: 'vehicle.label', default: 'Vehicle'), vehicle.id])
                redirect note
            }
            '*' { respond note, [status: CREATED] }
        }
    }

    def edit(Note note) {
        respond note
    }

    @Transactional
    def update(Note note) {
        if (note == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (note.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond note.errors, view:'edit'
            return
        }

        note.save flush:true

        request.withFormat {
            form multipartForm {
                //flash.message = message(code: 'default.updated.message', args: [message(code: 'vehicle.label', default: 'Vehicle'), vehicle.id])
                redirect note
            }
            '*'{ respond note, [status: OK] }
        }
    }

    @Transactional
    def delete(Note note) {

        if (note == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        note.delete flush:true

        request.withFormat {
            form multipartForm {
                //flash.message = message(code: 'default.deleted.message', args: [message(code: 'vehicle.label', default: 'Vehicle'), vehicle.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                //flash.message = message(code: 'default.not.found.message', args: [message(code: 'vehicle.label', default: 'Vehicle'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}