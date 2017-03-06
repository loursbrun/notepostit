package com.example

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GroupPublicController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def valueEstimateService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond GroupPublic.list(params), model:[groupPublic: GroupPublic.count()]
    }

    def show(GroupPublic groupPublic) {
        respond groupPublic
    }


    def create() {
        respond new GroupPublic(params)
    }

    @Transactional
    def save(GroupPublic groupPublic) {
        if (groupPublic == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (groupPublic.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond groupPublic.errors, view:'create'
            return
        }

        groupPublic.save flush:true

        request.withFormat {
            form multipartForm {
                //flash.message = message(code: 'default.created.message', args: [message(code: 'vehicle.label', default: 'Vehicle'), vehicle.id])
                redirect groupPublic
            }
            '*' { respond groupPublic, [status: CREATED] }
        }
    }

    def edit(GroupPublic groupPublic) {
        respond groupPublic
    }

    @Transactional
    def update(GroupPublic groupPublic) {
        if (groupPublic == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (groupPublic.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond groupPublic.errors, view:'edit'
            return
        }

        groupPublic.save flush:true

        request.withFormat {
            form multipartForm {
                //flash.message = message(code: 'default.updated.message', args: [message(code: 'vehicle.label', default: 'Vehicle'), vehicle.id])
                redirect groupPublic
            }
            '*'{ respond groupPublic, [status: OK] }
        }
    }

    @Transactional
    def delete(GroupPublic groupPublic) {

        if (groupPublic == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        groupPublic.delete flush:true

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
