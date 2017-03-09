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










        def note1 = new Note(name: "prendre un café", content: "fghjkjhgfghjklkjhg", urlImg: "dfgjkjhgfghjkjhgf.com", groupID: 1)
        def note2 = new Note(name: "Boire du vin" , content: "eeeeeeeeee", urlImg: "cccccccccc.com", groupID: 1)
        def note3 = new Note(name: "Conduire", content: "fghjkjhgfghjklkjhg", urlImg: "dfgjkjhgfghjkjhgf.com", groupID: 1)

        def group2 = new GroupPublic(name: "Pepsi", isPublic: true)
        group2.addToNotes(note1)
        group2.addToNotes(note2)
        group2.addToNotes(note3)
        group2.emails.add("fabienbrun@hotmail.fr")
        group2.emails.add("didier@hotmail.fr")
        group2.save()



        def note4 = new Note(name: "Aller au sport" , content: "eeeeeeeeee", urlImg: "cccccccccc.com", groupID: 2)
        def note5 = new Note(name: "Jouer aux cartes", content: "fghjkjhgfghjklkjhg", urlImg: "dfgjkjhgfghjkjhgf.com", groupID: 2)
        def note6 = new Note(name: "Prendre son temps" , content: "eeeeeeeeee", urlImg: "cccccccccc.com", groupID: 2)

        def group3 = new GroupPublic(name: "Fanta", isPublic: true)
        group3.addToNotes(note4)
        group3.addToNotes(note5)
        group3.addToNotes(note6)

        group3.emails.add("cococo")
        group3.emails.add("cvbfdsdfg")
        group3.emails.add("sdfgfdsdfgh")
        group3.emails.add("xcvccvb")
        group3.save()





        def note7 = new Note(name: "Courir", content: "fghjkjhgfghjklkjhg", urlImg: "dfgjkjhgfghjkjhgf.com", groupID: 3)
        def note8 = new Note(name: "Aller nage" , content: "eeeeeeeeee", urlImg: "cccccccccc.com", groupID: 3)
        def note9 = new Note(name: "Se mettre à l'abris", content: "fghjkjhgfghjklkjhg", urlImg: "dfgjkjhgfghjkjhgf.com", groupID: 3)
        def note10 = new Note(name: "Payer ses factures" , content: "eeeeeeeeee", urlImg: "cccccccccc.com", groupID: 3)
        def note11 = new Note(name: "Prendre la moto", content: "fghjkjhgfghjklkjhg", urlImg: "dfgjkjhgfghjkjhgf.com", groupID: 3)
        def note12 = new Note(name: "Aller à la montagne" , content: "eeeeeeeeee", urlImg: "cccccccccc.com", groupID: 3)


        def group4 = new GroupPublic(name: "Orangina", isPublic: true)
        group4.addToNotes(note7)
        group4.addToNotes(note8)
        group4.addToNotes(note9)
        group4.addToNotes(note10)
        group4.addToNotes(note11)
        group4.addToNotes(note12)
        group4.emails.add("cococo")
        group4.emails.add("cvbfdsdfg")
        group4.emails.add("sdfgfdsdfgh")
        group4.emails.add("xcvccvb")
        group4.save()


        def group5 = new GroupPublic(name: "Nework", isPublic: true)
        group5.save()

    }
    def destroy = {
    }
}
