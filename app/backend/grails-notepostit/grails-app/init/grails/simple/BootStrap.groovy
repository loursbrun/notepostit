package grails.simple

import com.example.GroupPublic
import com.example.Make
import com.example.Note
import com.example.Model
import com.example.User
import com.example.Vehicle
import net.sf.ehcache.search.expression.Not

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

        Boolean isPublic
        Boolean isPrivate



        def note1 = new Note(name: "Fabien", content: "fghjkjhgfghjklkjhg", urlImg: "dfgjkjhgfghjkjhgf.com")
        def note2 = new Note(name: "DEDE" , content: "eeeeeeeeee", urlImg: "cccccccccc.com")


        def goup1 = new GroupPublic(name: "Coco")
        goup1.addToNotes(note1)
        goup1.addToNotes(note2)
        goup1.emails.add("cococo")
        goup1.emails.add("cvbfdsdfg")
        goup1.emails.add("sdfgfdsdfgh")
        goup1.emails.add("xcvccvb")
        goup1.save()



    }
    def destroy = {
    }
}
