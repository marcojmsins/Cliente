package cliente

import grails.converters.JSON
import grails.validation.ValidationException
import groovy.json.JsonSlurper

import static org.springframework.http.HttpStatus.*

class AgencyController {

    AgencyService agencyService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond agencyService.list(params), model:[agencyCount: agencyService.count()]
    }


    def showAgency() {
        def url= new URL("http://localhost:3456/agencias?site_id=MLA&payment_method_id=rapipago&latitude=-31.412971&longitude=-64.187&radius=300&sortBy=distance")
        //def url = new URL("https://api.mercadolibre.com/sites/"+params.id+"/categories")
        def connection = (HttpURLConnection) url.openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "Mozilla/5.0")
        JsonSlurper json = new JsonSlurper()
        def result=json.parse(connection.getInputStream())



        print(result)

        def model = [
                agencies: result.data
        ]

        render(view:"index", model:model)
    }





    def show(Long id) {
        respond agencyService.get(id)
    }

    def create() {
        respond new Agency(params)
    }

    def save(Agency agency) {
        if (agency == null) {
            notFound()
            return
        }

        try {
            agencyService.save(agency)
        } catch (ValidationException e) {
            respond agency.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'agency.label', default: 'Agency'), agency.id])
                redirect agency
            }
            '*' { respond agency, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond agencyService.get(id)
    }

    def update(Agency agency) {
        if (agency == null) {
            notFound()
            return
        }

        try {
            agencyService.save(agency)
        } catch (ValidationException e) {
            respond agency.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'agency.label', default: 'Agency'), agency.id])
                redirect agency
            }
            '*'{ respond agency, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        agencyService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'agency.label', default: 'Agency'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'agency.label', default: 'Agency'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
