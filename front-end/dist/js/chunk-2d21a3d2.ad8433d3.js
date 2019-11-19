(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d21a3d2"],{bb51:function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return e.loaded?a("div",[a("v-container",{attrs:{"fill-height":"",fluid:"","grid-list-xl":""}},[a("v-layout",{attrs:{wrap:""}},[a("v-flex",{attrs:{sm6:"",xs12:"",md6:"",lg3:""}},[a("material-stats-card",{attrs:{color:"green",icon:"mdi-counter",title:"Loaded Snippets",value:e.statusSnippets.message,"sub-icon":"mdi-information-outline","sub-text":"The amount of  loaded snippets within Genesis"}})],1),a("v-flex",{attrs:{sm6:"",xs12:"",md6:"",lg3:""}},[a("material-stats-card",{attrs:{color:"green",icon:"mdi-format-italic",title:"Most used language",value:e.statusTopLanguage.message,"sub-icon":"mdi-information-outline","sub-text":"The most used language in all loaded snippets"}})],1),a("v-flex",{attrs:{sm6:"",xs12:"",md6:"",lg3:""}},[a("material-stats-card",{attrs:{color:"green",icon:"mdi-cube",title:"Most used Technique",value:e.statusTopTechnique.message,"sub-icon":"mdi-information-outline","sub-text":"The most used technique in all loaded snippets"}})],1),a("v-flex",{attrs:{sm6:"",xs12:"",md6:"",lg3:""}},[a("material-stats-card",{attrs:{color:"green",icon:"mdi-git",title:"Version",value:e.statusVersion.message,"sub-icon":"mdi-information-outline","sub-text":"The version of this instance of Genesis"}})],1),a("v-container",{attrs:{fluid:"","grid-list-xl":"","fill-height":""}},[a("v-layout",{attrs:{"justify-center":"","align-center":""}},[a("v-flex",{attrs:{xs12:""}},[a("material-card",{attrs:{color:"green"}},[a("div",{attrs:{slot:"header"},slot:"header"},[a("div",{staticClass:"title font-weight-light mb-2"},[e._v("Genesis")]),a("div",{staticClass:"category"},[a("strong",[e._v("Welcome to the home page!")])])]),a("h5",[e._v("\n              This website serves as a graphical user interface for the Java back-end. All communications go through the back-end API, where the request is processed and the requested value is returned. To generate a snippet based upon the provided selection, please head over to the "),a("a",{attrs:{href:"/generate-snippet"}},[e._v("Generate Snippet")]),e._v(" page. To add a new snippet to this instance of Genesis, please head over to the "),a("a",{attrs:{href:"add-snippet"}},[e._v("Add Snippet")]),e._v(" page."),a("br"),a("br"),e._v("\n              If any questions are raised, please refer to the "),a("a",{attrs:{href:"/faq"}},[e._v("Frequently Asked Questions")]),e._v(". If your problem is not resolved there, please do not hesitate to open an issue on the "),a("a",{attrs:{href:"GITHUB URL"}},[e._v("public Github repository")]),e._v(".\n            ")])])],1)],1)],1)],1)],1)],1):e._e()},i=[],r=(a("96cf"),a("3b8d")),n=a("4360"),o={data:function(){return{loaded:!1,dailySalesChart:{data:{labels:["M","T","W","T","F","S","S"],series:[[12,17,7,17,23,18,38]]},options:{lineSmooth:this.$chartist.Interpolation.cardinal({tension:0}),low:0,high:50,chartPadding:{top:0,right:0,bottom:0,left:0}}},dataCompletedTasksChart:{data:{labels:["12am","3pm","6pm","9pm","12pm","3am","6am","9am"],series:[[230,750,450,300,280,240,200,190]]},options:{lineSmooth:this.$chartist.Interpolation.cardinal({tension:0}),low:0,high:1e3,chartPadding:{top:0,right:0,bottom:0,left:0}}},emailsSubscriptionChart:{data:{labels:["Ja","Fe","Ma","Ap","Mai","Ju","Jul","Au","Se","Oc","No","De"],series:[[542,443,320,780,553,453,326,434,568,610,756,895]]},options:{axisX:{showGrid:!1},low:0,high:1e3,chartPadding:{top:0,right:5,bottom:0,left:0}},responsiveOptions:[["screen and (max-width: 640px)",{seriesBarDistance:5,axisX:{labelInterpolationFnc:function(e){return e[0]}}}]]},headers:[{sortable:!1,text:"ID",value:"id"},{sortable:!1,text:"Name",value:"name"},{sortable:!1,text:"Salary",value:"salary",align:"right"},{sortable:!1,text:"Country",value:"country",align:"right"},{sortable:!1,text:"City",value:"city",align:"right"}],items:[{name:"Dakota Rice",country:"Niger",city:"Oud-Tunrhout",salary:"$35,738"},{name:"Minerva Hooper",country:"Curaçao",city:"Sinaai-Waas",salary:"$23,738"},{name:"Sage Rodriguez",country:"Netherlands",city:"Overland Park",salary:"$56,142"},{name:"Philip Chanley",country:"Korea, South",city:"Gloucester",salary:"$38,735"},{name:"Doris Greene",country:"Malawi",city:"Feldkirchen in Kārnten",salary:"$63,542"}],tabs:0,list:{0:!1,1:!1,2:!1}}},computed:{statusTopTechnique:function(){return n["a"].getters["statusTopTechnique"]},statusTopLanguage:function(){return n["a"].getters["statusTopLanguage"]},statusVersion:function(){return n["a"].getters["statusVersion"]},statusSnippets:function(){return n["a"].getters["statusSnippets"]}},methods:{complete:function(e){this.list[e]=!this.list[e]}},mounted:function(){var e=Object(r["a"])(regeneratorRuntime.mark(function e(){return regeneratorRuntime.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return console.log(">>> LOADING HOME"),e.prev=1,e.next=4,n["a"].dispatch("retrieveAll");case 4:console.log(">>> HOME LOADED WITH SNIPPETS"),this.$data.loaded=!0,e.next=12;break;case 8:e.prev=8,e.t0=e["catch"](1),console.error(e.t0),toastr.error(e.t0);case 12:case"end":return e.stop()}},e,this,[[1,8]])}));function t(){return e.apply(this,arguments)}return t}()},l=o,u=a("2877"),c=Object(u["a"])(l,s,i,!1,null,null,null);t["default"]=c.exports}}]);
//# sourceMappingURL=chunk-2d21a3d2.ad8433d3.js.map