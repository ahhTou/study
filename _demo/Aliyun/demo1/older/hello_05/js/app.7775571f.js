(function(e){function t(t){for(var r,o,c=t[0],u=t[1],s=t[2],l=0,d=[];l<c.length;l++)o=c[l],Object.prototype.hasOwnProperty.call(i,o)&&i[o]&&d.push(i[o][0]),i[o]=0;for(r in u)Object.prototype.hasOwnProperty.call(u,r)&&(e[r]=u[r]);f&&f(t);while(d.length)d.shift()();return a.push.apply(a,s||[]),n()}function n(){for(var e,t=0;t<a.length;t++){for(var n=a[t],r=!0,o=1;o<n.length;o++){var c=n[o];0!==i[c]&&(r=!1)}r&&(a.splice(t--,1),e=u(u.s=n[0]))}return e}var r={},o={app:0},i={app:0},a=[];function c(e){return u.p+"js/"+({}[e]||e)+"."+{"chunk-188921b8":"8f16d739","chunk-2d0c8e41":"a27e58e6","chunk-525c598a":"9139f515","chunk-97256772":"27caa1c0"}[e]+".js"}function u(t){if(r[t])return r[t].exports;var n=r[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,u),n.l=!0,n.exports}u.e=function(e){var t=[],n={"chunk-188921b8":1,"chunk-525c598a":1,"chunk-97256772":1};o[e]?t.push(o[e]):0!==o[e]&&n[e]&&t.push(o[e]=new Promise((function(t,n){for(var r="css/"+({}[e]||e)+"."+{"chunk-188921b8":"d8164f42","chunk-2d0c8e41":"31d6cfe0","chunk-525c598a":"5b38611e","chunk-97256772":"6cb836f6"}[e]+".css",i=u.p+r,a=document.getElementsByTagName("link"),c=0;c<a.length;c++){var s=a[c],l=s.getAttribute("data-href")||s.getAttribute("href");if("stylesheet"===s.rel&&(l===r||l===i))return t()}var d=document.getElementsByTagName("style");for(c=0;c<d.length;c++){s=d[c],l=s.getAttribute("data-href");if(l===r||l===i)return t()}var f=document.createElement("link");f.rel="stylesheet",f.type="text/css",f.onload=t,f.onerror=function(t){var r=t&&t.target&&t.target.src||i,a=new Error("Loading CSS chunk "+e+" failed.\n("+r+")");a.code="CSS_CHUNK_LOAD_FAILED",a.request=r,delete o[e],f.parentNode.removeChild(f),n(a)},f.href=i;var h=document.getElementsByTagName("head")[0];h.appendChild(f)})).then((function(){o[e]=0})));var r=i[e];if(0!==r)if(r)t.push(r[2]);else{var a=new Promise((function(t,n){r=i[e]=[t,n]}));t.push(r[2]=a);var s,l=document.createElement("script");l.charset="utf-8",l.timeout=120,u.nc&&l.setAttribute("nonce",u.nc),l.src=c(e);var d=new Error;s=function(t){l.onerror=l.onload=null,clearTimeout(f);var n=i[e];if(0!==n){if(n){var r=t&&("load"===t.type?"missing":t.type),o=t&&t.target&&t.target.src;d.message="Loading chunk "+e+" failed.\n("+r+": "+o+")",d.name="ChunkLoadError",d.type=r,d.request=o,n[1](d)}i[e]=void 0}};var f=setTimeout((function(){s({type:"timeout",target:l})}),12e4);l.onerror=l.onload=s,document.head.appendChild(l)}return Promise.all(t)},u.m=e,u.c=r,u.d=function(e,t,n){u.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},u.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},u.t=function(e,t){if(1&t&&(e=u(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(u.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var r in e)u.d(n,r,function(t){return e[t]}.bind(null,r));return n},u.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return u.d(t,"a",t),t},u.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},u.p="/",u.oe=function(e){throw console.error(e),e};var s=window["webpackJsonp"]=window["webpackJsonp"]||[],l=s.push.bind(s);s.push=t,s=s.slice();for(var d=0;d<s.length;d++)t(s[d]);var f=l;a.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"034f":function(e,t,n){"use strict";var r=n("85ec"),o=n.n(r);o.a},"039e":function(e,t,n){},1014:function(e,t,n){"use strict";n.r(t);var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("shade-slots",{scopedSlots:e._u([{key:"center",fn:function(){return[n("div",{attrs:{id:"changerWrapper"}},[n("div",{attrs:{id:"title"}},[e._v(" 更 换 背 景 ")]),n("div",{ref:"bgChangerContent",attrs:{id:"content"},on:{click:function(e){e.stopPropagation()}}},e._l(e.bgImageList,(function(t){return n("div",{staticClass:"skin",style:[e.skinItemStyle,e.skinBg(t)],on:{click:function(n){return n.stopPropagation(),e.clickToChangeBg(t)}}})})),0)])]},proxy:!0}])})},o=[],i=n("867d"),a={name:"BgChanger",components:{ShadeSlots:i["a"]},data:function(){return{bgData:null,bgImageList:{bg1:"http://39.99.154.145/img/hello/bg1.jpg",bg2:"http://39.99.154.145/img/hello/bg2.jpg",bg3:"http://39.99.154.145/img/hello/bg3.jpg",bg4:"http://39.99.154.145/img/hello/bg4.jpg"},skinItemStyle:null,skinItemBg:null,isPrimeWindowOpen:!1}},mounted:function(){var e=this;setTimeout((function(){e.computeSkin()}))},methods:{skinBg:function(e){return{backgroundImage:'url("'+e+'")'}},computeSkin:function(){var e=this.$refs.bgChangerContent;this.skinItemStyle={"--item-width":Math.round(.3*e.offsetWidth)+"px","--item-height":Math.round(.8*e.offsetHeight)+"px",backgroundSize:"cover",backgroundPosition:"center"}},clickToChangeBg:function(e){this.$store.commit("changeBg",e),console.log(this.$store.state.bgUrl)}}},c=a,u=(n("d966"),n("2877")),s=Object(u["a"])(c,r,o,!1,null,"82b7f344",null);t["default"]=s.exports},"449b":function(e,t,n){"use strict";var r=n("e21f"),o=n.n(r);o.a},"56d7":function(e,t,n){"use strict";n.r(t);n("c975"),n("e260"),n("e6cf"),n("cca6"),n("a79d");var r=n("2b0e"),o=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("router-view")],1)},i=[],a=(n("034f"),n("2877")),c={},u=Object(a["a"])(c,o,i,!1,null,null,null),s=u.exports,l=(n("d3b7"),n("8c4f")),d=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{ref:"homeWrapper",attrs:{id:"homeWrapper"}},[n("div",{attrs:{id:"title"},on:{click:function(t){return e.$router.push("/PrimeMenu")}}},[e._v(" Hello, ahhTou ")]),n("div",{attrs:{id:"btnLine"}},[n("div",{staticClass:"btn",on:{click:function(t){return e.$router.push("/Register")}}},[e._v("注册")]),n("div",{staticClass:"btn",attrs:{id:"board"},on:{click:function(t){return e.$router.push("/MsgBoard")}}},[e._v("留言板")]),n("div",{staticClass:"btn",on:{click:function(t){return e.$router.push("/BgChanger")}}},[n("span",{staticClass:"icon iconfont icon-skin"})])]),n("bg-shower"),n("router-view")],1)},f=[],h=n("1014"),p=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{style:[e.base,e.bgData],attrs:{id:"bgShower"}})},g=[],m={name:"BgShower",data:function(){return{bgData:null,base:{backgroundSize:"cover",backgroundPosition:"center"}}},mounted:function(){this.check()},methods:{check:function(){var e=window.localStorage.getItem("theHelloBg");this.bgData=null===e||""===e||void 0===e?{backgroundImage:'url("'+this.$store.state.bgUrl+'")'}:{backgroundImage:'url("'+e+'")'}}},computed:{bgDataFromVuex:function(){return this.$store.state.bgUrl}},watch:{bgDataFromVuex:function(e){this.bgData={backgroundImage:'url("'+e+'")'},window.localStorage.setItem("theHelloBg",e)}}},b=m,v=(n("a0b6"),Object(a["a"])(b,p,g,!1,null,"52412dbd",null)),k=v.exports,y={name:"Home",components:{BgShower:k,BgChanger:h["default"]},data:function(){return{isPrimeWindowOpen:!1}},methods:{openPrimeWindow:function(){this.isPrimeWindowOpen=!this.isPrimeWindowOpen}}},w=y,S=(n("449b"),Object(a["a"])(w,d,f,!1,null,"fb67640e",null)),_=S.exports;r["a"].use(l["a"]);var O=[{path:"/",name:"Home",component:_,meta:{title:"欢迎"},children:[{path:"/PrimeMenu",name:"PrimeMenu",meta:{title:"老demo",EnterType:"center"},component:function(){return n.e("chunk-525c598a").then(n.bind(null,"c5f5"))}},{path:"/MsgBoard",name:"MsgBoard",meta:{title:"留言板",EnterType:"centerWithScroll"},component:function(){return n.e("chunk-188921b8").then(n.bind(null,"79fc"))}},{path:"/BgChanger",name:"BgChanger",meta:{title:"切换背景",EnterType:"center"},component:function(){return Promise.resolve().then(n.bind(null,"1014"))}},{path:"/Register",name:"Register",meta:{title:"注册",EnterType:"card"},component:function(){return n.e("chunk-97256772").then(n.bind(null,"1feb"))}},{path:"/Login",name:"Login",meta:{title:"登录",EnterType:"card"},component:function(){return n.e("chunk-2d0c8e41").then(n.bind(null,"578a"))}}]}],T=new l["a"]({mode:"history",base:"hello",routes:O});T.beforeEach((function(e,t,n){document.title=e.meta.title,n()}));var x=T,C=n("2f62");r["a"].use(C["a"]);var P=new C["a"].Store({state:{bgUrl:null},mutations:{changeBg:function(e,t){e.bgUrl=t}},actions:{},modules:{}}),B=(n("5f60"),n("fe3c")),E=n.n(B);E.a.prototype.focus=function(e){var t;e.setSelectionRange&&0!==e.type.indexOf("date")&&"time"!==e.type&&"month"!==e.type?(t=e.value.length,e.focus(),e.setSelectionRange(t,t)):e.focus()},E.a.attach(document.body),r["a"].config.productionTip=!1,new r["a"]({router:x,store:P,render:function(e){return e(s)}}).$mount("#app")},"5f60":function(e,t,n){},"85ec":function(e,t,n){},8614:function(e,t,n){"use strict";var r=n("b13c"),o=n.n(r);o.a},"867d":function(e,t,n){"use strict";var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"wrapper"}},[n("transition",{attrs:{name:"fade"}},[e.isOpen?n("div",{attrs:{id:"shade"},on:{click:e.exit}}):e._e()]),n("transition",{attrs:{name:"bigger"}},[e.isOpen&&e.enterType.center?n("div",{attrs:{id:"center"},on:{click:e.exit}},[e._t("center")],2):e._e()]),n("transition",{attrs:{name:"fade"}},[e.isOpen&&e.enterType.centerWithScroll?n("div",{attrs:{id:"centerWithScroll"},on:{click:e.exit}},[e._t("content"),n("div",{attrs:{id:"controller"},on:{click:e.exit}},[e._t("controller")],2)],2):e._e()]),n("transition",{attrs:{name:"card-with-slider"}},[e.isOpen&&e.enterType.card?n("div",{attrs:{id:"cardWithSlider"},on:{click:e.exit}},[e._t("card")],2):e._e()])],1)},o=[],i={name:"ShadeSlots",data:function(){return{classData:"close",isOpen:!1,timer:200,enterType:{centerWithScroll:!1,center:!1,card:!1}}},methods:{exit:function(){var e=this;this.$nextTick((function(){e.isOpen=!1,setTimeout((function(){e.$router.push("/")}),e.timer)}))}},mounted:function(){var e=this;this.$nextTick((function(){switch(e.isOpen=!0,e.$route.meta.EnterType){case"card":e.enterType.card=!0;break;case"center":e.enterType.center=!0;break;case"centerWithScroll":e.enterType.centerWithScroll=!0;break}}))}},a=i,c=(n("8614"),n("2877")),u=Object(c["a"])(a,r,o,!1,null,"62174f10",null);t["a"]=u.exports},"9f38":function(e,t,n){},a0b6:function(e,t,n){"use strict";var r=n("039e"),o=n.n(r);o.a},b13c:function(e,t,n){},d966:function(e,t,n){"use strict";var r=n("9f38"),o=n.n(r);o.a},e21f:function(e,t,n){}});
//# sourceMappingURL=app.7775571f.js.map