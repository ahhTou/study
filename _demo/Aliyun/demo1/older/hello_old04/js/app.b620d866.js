(function(t){function e(e){for(var r,o,c=e[0],u=e[1],s=e[2],l=0,d=[];l<c.length;l++)o=c[l],Object.prototype.hasOwnProperty.call(i,o)&&i[o]&&d.push(i[o][0]),i[o]=0;for(r in u)Object.prototype.hasOwnProperty.call(u,r)&&(t[r]=u[r]);f&&f(e);while(d.length)d.shift()();return a.push.apply(a,s||[]),n()}function n(){for(var t,e=0;e<a.length;e++){for(var n=a[e],r=!0,o=1;o<n.length;o++){var c=n[o];0!==i[c]&&(r=!1)}r&&(a.splice(e--,1),t=u(u.s=n[0]))}return t}var r={},o={app:0},i={app:0},a=[];function c(t){return u.p+"js/"+({}[t]||t)+"."+{"chunk-60271301":"ba42fdbf","chunk-72de455d":"a75a2d3b"}[t]+".js"}function u(e){if(r[e])return r[e].exports;var n=r[e]={i:e,l:!1,exports:{}};return t[e].call(n.exports,n,n.exports,u),n.l=!0,n.exports}u.e=function(t){var e=[],n={"chunk-60271301":1,"chunk-72de455d":1};o[t]?e.push(o[t]):0!==o[t]&&n[t]&&e.push(o[t]=new Promise((function(e,n){for(var r="css/"+({}[t]||t)+"."+{"chunk-60271301":"575c89f8","chunk-72de455d":"dcb2956c"}[t]+".css",i=u.p+r,a=document.getElementsByTagName("link"),c=0;c<a.length;c++){var s=a[c],l=s.getAttribute("data-href")||s.getAttribute("href");if("stylesheet"===s.rel&&(l===r||l===i))return e()}var d=document.getElementsByTagName("style");for(c=0;c<d.length;c++){s=d[c],l=s.getAttribute("data-href");if(l===r||l===i)return e()}var f=document.createElement("link");f.rel="stylesheet",f.type="text/css",f.onload=e,f.onerror=function(e){var r=e&&e.target&&e.target.src||i,a=new Error("Loading CSS chunk "+t+" failed.\n("+r+")");a.code="CSS_CHUNK_LOAD_FAILED",a.request=r,delete o[t],f.parentNode.removeChild(f),n(a)},f.href=i;var h=document.getElementsByTagName("head")[0];h.appendChild(f)})).then((function(){o[t]=0})));var r=i[t];if(0!==r)if(r)e.push(r[2]);else{var a=new Promise((function(e,n){r=i[t]=[e,n]}));e.push(r[2]=a);var s,l=document.createElement("script");l.charset="utf-8",l.timeout=120,u.nc&&l.setAttribute("nonce",u.nc),l.src=c(t);var d=new Error;s=function(e){l.onerror=l.onload=null,clearTimeout(f);var n=i[t];if(0!==n){if(n){var r=e&&("load"===e.type?"missing":e.type),o=e&&e.target&&e.target.src;d.message="Loading chunk "+t+" failed.\n("+r+": "+o+")",d.name="ChunkLoadError",d.type=r,d.request=o,n[1](d)}i[t]=void 0}};var f=setTimeout((function(){s({type:"timeout",target:l})}),12e4);l.onerror=l.onload=s,document.head.appendChild(l)}return Promise.all(e)},u.m=t,u.c=r,u.d=function(t,e,n){u.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:n})},u.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},u.t=function(t,e){if(1&e&&(t=u(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var n=Object.create(null);if(u.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var r in t)u.d(n,r,function(e){return t[e]}.bind(null,r));return n},u.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return u.d(e,"a",e),e},u.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},u.p="/",u.oe=function(t){throw console.error(t),t};var s=window["webpackJsonp"]=window["webpackJsonp"]||[],l=s.push.bind(s);s.push=e,s=s.slice();for(var d=0;d<s.length;d++)e(s[d]);var f=l;a.push([0,"chunk-vendors"]),n()})({0:function(t,e,n){t.exports=n("56d7")},"034f":function(t,e,n){"use strict";var r=n("85ec"),o=n.n(r);o.a},"039e":function(t,e,n){},1014:function(t,e,n){"use strict";n.r(e);var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("shade-slots",{scopedSlots:t._u([{key:"center",fn:function(){return[n("div",{attrs:{id:"changerWrapper"}},[n("div",{attrs:{id:"title"}},[t._v(" 更 换 背 景 ")]),n("div",{ref:"bgChangerContent",attrs:{id:"content"},on:{click:function(t){t.stopPropagation()}}},t._l(t.bgImageList,(function(e){return n("div",{staticClass:"skin",style:[t.skinItemStyle,t.skinBg(e)],on:{click:function(n){return n.stopPropagation(),t.clickToChangeBg(e)}}})})),0)])]},proxy:!0}])})},o=[],i=n("867d"),a={name:"BgChanger",components:{ShadeSlots:i["a"]},data:function(){return{bgData:null,bgImageList:{bg1:"http://39.99.154.145/img/hello/bg1.jpg",bg2:"http://39.99.154.145/img/hello/bg2.jpg",bg3:"http://39.99.154.145/img/hello/bg3.jpg",bg4:"http://39.99.154.145/img/hello/bg4.jpg"},skinItemStyle:null,skinItemBg:null,isPrimeWindowOpen:!1}},mounted:function(){var t=this;setTimeout((function(){t.computeSkin()}))},methods:{skinBg:function(t){return{backgroundImage:'url("'+t+'")'}},computeSkin:function(){var t=this.$refs.bgChangerContent;this.skinItemStyle={"--item-width":Math.round(.3*t.offsetWidth)+"px","--item-height":Math.round(.8*t.offsetHeight)+"px",backgroundSize:"cover",backgroundPosition:"center"}},clickToChangeBg:function(t){this.$store.commit("changeBg",t),console.log(this.$store.state.bgUrl)}}},c=a,u=(n("d966"),n("2877")),s=Object(u["a"])(c,r,o,!1,null,"82b7f344",null);e["default"]=s.exports},"1ab7":function(t,e,n){"use strict";var r=n("3f4e"),o=n.n(r);o.a},"1af6":function(t,e,n){},"21e0":function(t,e,n){"use strict";var r=n("1af6"),o=n.n(r);o.a},"3f4e":function(t,e,n){},"56d7":function(t,e,n){"use strict";n.r(e);n("c975"),n("e260"),n("e6cf"),n("cca6"),n("a79d");var r=n("2b0e"),o=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{attrs:{id:"app"}},[n("router-view")],1)},i=[],a=(n("034f"),n("2877")),c={},u=Object(a["a"])(c,o,i,!1,null,null,null),s=u.exports,l=(n("d3b7"),n("8c4f")),d=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{ref:"homeWrapper",attrs:{id:"homeWrapper"}},[n("div",{attrs:{id:"title"},on:{click:function(e){return t.$router.push("/PrimeMenu")}}},[t._v(" Hello, ahhTou ")]),n("div",{attrs:{id:"btnLine"}},[n("div",{staticClass:"btn",attrs:{id:"board"},on:{click:function(e){return t.$router.push("/MsgBoard")}}},[t._v("留言板")]),n("div",{staticClass:"btn",on:{click:function(e){return t.$router.push("/BgChanger")}}},[t._v("切换背景")])]),n("bg-shower"),n("router-view")],1)},f=[],h=n("1014"),g=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{style:[t.base,t.bgData],attrs:{id:"bgShower"}})},p=[],m={name:"BgShower",data:function(){return{bgData:null,base:{backgroundSize:"cover",backgroundPosition:"center"}}},mounted:function(){this.check()},methods:{check:function(){var t=window.localStorage.getItem("theHelloBg");this.bgData=null===t||""===t||void 0===t?{backgroundImage:'url("'+this.$store.state.bgUrl+'")'}:{backgroundImage:'url("'+t+'")'}}},computed:{bgDataFromVuex:function(){return this.$store.state.bgUrl}},watch:{bgDataFromVuex:function(t){this.bgData={backgroundImage:'url("'+t+'")'},window.localStorage.setItem("theHelloBg",t)}}},b=m,v=(n("a0b6"),Object(a["a"])(b,g,p,!1,null,"52412dbd",null)),k=v.exports,y={name:"Home",components:{BgShower:k,BgChanger:h["default"]},data:function(){return{isPrimeWindowOpen:!1}},methods:{openPrimeWindow:function(){this.isPrimeWindowOpen=!this.isPrimeWindowOpen}}},w=y,_=(n("21e0"),Object(a["a"])(w,d,f,!1,null,"43e62a57",null)),S=_.exports;r["a"].use(l["a"]);var O=[{path:"/",name:"Home",component:S,meta:{title:"欢迎"},children:[{path:"/PrimeMenu",name:"PrimeMenu",meta:{title:"老demo"},component:function(){return n.e("chunk-72de455d").then(n.bind(null,"c5f5"))}},{path:"/MsgBoard",name:"MsgBoard",meta:{title:"留言板"},component:function(){return n.e("chunk-60271301").then(n.bind(null,"79fc"))}},{path:"/BgChanger",name:"BgChanger",meta:{title:"切换背景"},component:function(){return Promise.resolve().then(n.bind(null,"1014"))}}]}],x=new l["a"]({mode:"history",base:"hello",routes:O});x.beforeEach((function(t,e,n){document.title=t.meta.title,n()}));var P=x,B=n("2f62");r["a"].use(B["a"]);var C=new B["a"].Store({state:{bgUrl:null},mutations:{changeBg:function(t,e){t.bgUrl=e}},actions:{},modules:{}}),j=n("fe3c"),$=n.n(j);$.a.prototype.focus=function(t){var e;t.setSelectionRange&&0!==t.type.indexOf("date")&&"time"!==t.type&&"month"!==t.type?(e=t.value.length,t.focus(),t.setSelectionRange(e,e)):t.focus()},$.a.attach(document.body),r["a"].config.productionTip=!1,new r["a"]({router:P,store:C,render:function(t){return t(s)}}).$mount("#app")},"85ec":function(t,e,n){},"867d":function(t,e,n){"use strict";var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{attrs:{id:"wrapper"}},[n("transition",{attrs:{name:"fade"}},[t.isOpen?n("div",{attrs:{id:"shade"},on:{click:t.exit}}):t._e()]),n("transition",{attrs:{name:"bigger"}},[t.isOpen?n("div",{attrs:{id:"center"},on:{click:t.exit}},[t._t("center")],2):t._e()]),n("transition",{attrs:{name:"fade"}},[t.isOpen?n("div",{attrs:{id:"centerWithScroll"},on:{click:t.exit}},[t._t("content"),n("div",{attrs:{id:"controller"},on:{click:t.exit}},[t._t("controller")],2)],2):t._e()])],1)},o=[],i={name:"ShadeSlots",data:function(){return{classData:"close",isOpen:!1}},methods:{exit:function(){var t=this;this.$nextTick((function(){t.isOpen=!1,setTimeout((function(){t.$router.go(-1)}),300)}))}},mounted:function(){var t=this;this.$nextTick((function(){t.isOpen=!0}))}},a=i,c=(n("1ab7"),n("2877")),u=Object(c["a"])(a,r,o,!1,null,"05dbb26f",null);e["a"]=u.exports},"9f38":function(t,e,n){},a0b6:function(t,e,n){"use strict";var r=n("039e"),o=n.n(r);o.a},d966:function(t,e,n){"use strict";var r=n("9f38"),o=n.n(r);o.a}});
//# sourceMappingURL=app.b620d866.js.map