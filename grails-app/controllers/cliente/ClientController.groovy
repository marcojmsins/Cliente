package cliente

import groovy.json.JsonSlurper

class ClientController {

    def index() {



        def url = new URL("http://localhost:3456/agencias?site_id="+params.site_id+"&payment_method_id="+ params.payment_method +
                "&latitude="+ params.latitude +"&longitude="+ params.longitude +"&radius="+ params.offest +"&sortBy=distance")
        def connection = (HttpURLConnection) url.openConnection()

//asociamos a connetion un setrequestmethod y en el metodo le ponemos get, ya que ml en el metodo nos devuelve los sites
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/jason")
        connection.setRequestProperty("User-Agent", "Mozilla/5.0")

//castea el JSon similar al Gson, con JsonSlurper
        JsonSlurper json = new JsonSlurper()
        def result = json.parse(connection.getInputStream())

//buscar parsear jsonslurper to array.

        print(result)


        def model = [
                agencies: result.data
        ]
        render(view:"clientresponse", model:model)
    }

    def addAgency(Agency){


    }
}