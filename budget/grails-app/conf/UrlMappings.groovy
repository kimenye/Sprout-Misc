class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

//		"/"(controller:"plan", action: "begin")
//        "/"(controller:"budget", action: "show")
//        "/"(controller:"budget", action: "show")
//        "/" (view: '/plan/index')
        "/" (controller: "plan", action: "index")
		"500"(view:'/error')
	}
}
