package grails.simple

import com.example.Make
import com.example.Model
import com.example.User
import com.example.Vehicle

class BootStrap {

    def init = { servletContext ->

        def nissan = new Make(name: "Nissan").save()
        def ford = new Make(name: "Ford").save()

        def titan = new Model(name: "Titan", make: nissan).save()
        def leaf = new Model(name: "Leaf", make: nissan).save()
        def windstar = new Model(name: "Windstar", make: ford).save()

        new Vehicle(name: "Pickup",  make: nissan, model: titan, year: 2012).save()
        new Vehicle(name: "Economy", make: nissan, model: leaf, year: 2014).save()
        new Vehicle(name: "Minivan", make: ford, model: windstar, year: 1990).save()



        // Création des users
        def user1 = new User(name: "Fabien", password: "pass1",email:"loursbrun@hotmail.fr",logged:false).save()
        def user2 = new User(name: "Gilou", password: "pass2",email:"gilou@hotmail.fr",logged:true).save()
        def user3 = new User(name: "Bernard", password: "pass3",email:"bernard@hotmail.fr",logged:false).save()




    }
    def destroy = {
    }
}
