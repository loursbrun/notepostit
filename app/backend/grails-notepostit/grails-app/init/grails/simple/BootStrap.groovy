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


        def group1 = new GroupPublic(name: "Coca", isPublic: true)
        group1.addToNotes(note1)
        group1.addToNotes(note2)
        group1.emails.add("cococo")
        group1.emails.add("cvbfdsdfg")
        group1.emails.add("sdfgfdsdfgh")
        group1.emails.add("xcvccvb")
        group1.save()

        def note3 = new Note(name: "Fabien", content: "fghjkjhgfghjklkjhg", urlImg: "dfgjkjhgfghjkjhgf.com")
        def note4 = new Note(name: "DEDE" , content: "eeeeeeeeee", urlImg: "cccccccccc.com")

        def group2 = new GroupPublic(name: "Pepsi", isPublic: true)
        group2.addToNotes(note3)
        group2.addToNotes(note4)
        group2.emails.add("cococo")
        group2.emails.add("cvbfdsdfg")
        group2.emails.add("sdfgfdsdfgh")
        group2.emails.add("xcvccvb")
        group2.save()

        def group3 = new GroupPublic(name: "Fanta", isPublic: true)
        group3.addToNotes(note1)
        group3.addToNotes(note2)
        group3.emails.add("cococo")
        group3.emails.add("cvbfdsdfg")
        group3.emails.add("sdfgfdsdfgh")
        group3.emails.add("xcvccvb")
        group3.save()

        def group4 = new GroupPublic(name: "Orangina", isPublic: true)
        group4.addToNotes(note1)
        group4.addToNotes(note2)
        group4.emails.add("cococo")
        group4.emails.add("cvbfdsdfg")
        group4.emails.add("sdfgfdsdfgh")
        group4.emails.add("xcvccvb")
        group4.save()



    }
    def destroy = {
    }
}
