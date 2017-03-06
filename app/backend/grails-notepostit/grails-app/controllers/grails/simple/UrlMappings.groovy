package grails.simple

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        //"/"(controller:"home")
        "/"(controller:"user")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
