import ratpack.form.Form

import static ratpack.groovy.Groovy.groovyTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {
    modules {
        register new GstormModule()
    }
    handlers {
        get {
            blocking {
                Item.all
            } then {
                render groovyTemplate("index.html", title: "Groovy Track My Stuff", items: it)
            }
        }

        get("new") {
            render groovyTemplate("new.html", title: "Groovy Track My Stuff")
        }

        post ("submit") {
            def form = parse(Form)

            def item = new Item(name: form.name, itemType: form.item_type,
                    itemLocation_1: form.item_location_1, itemLocation_2: form.item_location_2,
                    description: form.description,
                    lastUpdatedAt: new Date())
            blocking {
                item.save()
            } then {
                redirect("/")
            }
        }

        get("edit/:id"){
            blocking {
                Item.get(pathTokens.id)
            } then {
                render groovyTemplate("edit.html", title: "Groovy Track My Stuff", id: it.id, name: it.name,
                        itemType: it.itemType, itemLocation_1: it.itemLocation_1, itemLocation_2: it.itemLocation_2,
                        description: it.description)
            }
        }

        post ("update/item/:id") {
            def form = parse(Form)

            def item = new Item(id: form.id, name: form.name, itemType: form.item_type,
                    itemLocation_1: form.item_location_1, itemLocation_2: form.item_location_2,
                    description: form.description,
                    lastUpdatedAt: new Date())

            blocking {
                item.save()
            } then {
                redirect("/")
            }
        }

        get("delete/:id"){
            render groovyTemplate("delete.html", id: "${pathTokens.id}")
        }

        post ("delete/item/:id") {
            blocking {
                def item = Item.get(pathTokens.id)
                def newItem = new Item(id: item.id, name: item.name)
                newItem.delete()
            } then {
                redirect("/")
            }
        }

        assets "public"
    }
}
