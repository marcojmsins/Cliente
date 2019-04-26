package cliente

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }


        "/responseClient" (controller: "Client")
        "/"(view: "/client/client")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
