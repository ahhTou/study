(function(e){function t(t){for(var i,a,o=t[0],c=t[1],u=t[2],d=0,p=[];d<o.length;d++)a=o[d],Object.prototype.hasOwnProperty.call(r,a)&&r[a]&&p.push(r[a][0]),r[a]=0;for(i in c)Object.prototype.hasOwnProperty.call(c,i)&&(e[i]=c[i]);l&&l(t);while(p.length)p.shift()();return s.push.apply(s,u||[]),n()}function n(){for(var e,t=0;t<s.length;t++){for(var n=s[t],i=!0,o=1;o<n.length;o++){var c=n[o];0!==r[c]&&(i=!1)}i&&(s.splice(t--,1),e=a(a.s=n[0]))}return e}var i={},r={app:0},s=[];function a(t){if(i[t])return i[t].exports;var n=i[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,a),n.l=!0,n.exports}a.m=e,a.c=i,a.d=function(e,t,n){a.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},a.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},a.t=function(e,t){if(1&t&&(e=a(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(a.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var i in e)a.d(n,i,function(t){return e[t]}.bind(null,i));return n},a.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return a.d(t,"a",t),t},a.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},a.p="";var o=window["webpackJsonp"]=window["webpackJsonp"]||[],c=o.push.bind(o);o.push=t,o=o.slice();for(var u=0;u<o.length;u++)t(o[u]);var l=c;s.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"034f":function(e,t,n){"use strict";var i=n("85ec"),r=n.n(i);r.a},"42df":function(e,t,n){},5475:function(e,t,n){"use strict";var i=n("8468"),r=n.n(i);r.a},"56d7":function(e,t,n){"use strict";n.r(t);n("c975"),n("e260"),n("e6cf"),n("cca6"),n("a79d");var i=n("2b0e"),r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("router-view")],1)},s=[],a=(n("034f"),n("2877")),o={},c=Object(a["a"])(o,r,s,!1,null,null,null),u=c.exports,l=n("8c4f"),d=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{ref:"homeWrapper",attrs:{id:"homeWrapper"}},[n("div",{attrs:{id:"title"},on:{click:e.openPrimeWindow}},[e._v(" Hello, ahhTou ")]),n("div",{staticClass:"safeRoom"},[n("prime-menu",{attrs:{isOpen:e.isPrimeWindowOpen}})],1),n("div",{staticClass:"safeRoom"},[n("msg-board"),n("bg-changer")],1)])},p=[],f=function(){var e=this,t=e.$createElement,n=e._self._c||t;return e.isOpenInner?n("div",{ref:"primeMenuWrapper",attrs:{id:"primeMenuWrapper"},on:{click:function(t){return t.stopPropagation(),e.close(t)}}},[n("div",{attrs:{id:"wrapper"}},[e._m(0),n("div",{attrs:{id:"content"}},e._l(e.websiteData,(function(t){return n("a",{attrs:{href:t.url}},[e._v(" "+e._s(t.title)+" ")])})),0)])]):e._e()},g=[function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"title"}},[n("strong",[e._v("曾经的demo")])])}],h={name:"PrimeMenu",props:{isOpen:Boolean},data:function(){return{websiteData:{web1:{title:"彩虹六号",about:"大一网页设计课期末作业",url:"http://39.99.154.145/prime/r6s/"},web2:{title:"Hello,ahhTou",about:"第一次学习Jquery的小Demo",url:"http://39.99.154.145/prime/jq/"}},isOpenInner:!1}},methods:{close:function(){var e=this;this.$nextTick((function(){var t=e.$refs.primeMenuWrapper;t.style.opacity="0",t.style.transform="scale(1.3)",setTimeout((function(){e.isOpenInner=!1,t.style.transform="scale(1.3)"}),300)}))},open:function(){var e=this;this.isOpenInner=!0,this.$nextTick((function(){setTimeout((function(){var t=e.$refs.primeMenuWrapper;t.style.transition="all 0.3s",t.style.opacity="1",t.style.transform="scale(1.0)"}),1)}))}},watch:{isOpen:function(){this.open()}}},m=h,v=(n("99ba"),Object(a["a"])(m,f,g,!1,null,"7b1bfea8",null)),b=v.exports,w=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{ref:"bgChangerWrapperRef",style:e.bgData,attrs:{id:"bgChangerWrapper"},on:{click:e.closeBgWindow}},[n("transition",{attrs:{name:"fade"}},[n("div",{directives:[{name:"show",rawName:"v-show",value:e.isOpen,expression:"isOpen"}],attrs:{id:"shade"}})]),n("div",{ref:"skinWindow",style:e.changerStyle,attrs:{id:"changeBgBtn"},on:{click:function(t){return t.stopPropagation(),e.changeBg(t)}}},[n("transition",[n("div",{directives:[{name:"show",rawName:"v-show",value:e.isOpen,expression:"isOpen"}],attrs:{id:"title"}},[e._v(" 更 换 背 景 ")])]),n("div",{ref:"bgChangerContent",attrs:{id:"content"}},e._l(e.bgImageList,(function(t){return n("div",{directives:[{name:"show",rawName:"v-show",value:e.isOpen,expression:"isOpen"}],staticClass:"skin",style:[e.skinItemStyle,e.skinBg(t)],on:{click:function(n){return n.stopPropagation(),e.clickToChangeBg(t)}}})})),0)],1),n("div",{staticStyle:{position:"absolute","z-index":"-100"}})],1)},O=[],S={name:"BgChanger",data:function(){return{bgData:null,bgImageList:{bg1:"http://39.99.154.145/img/hello/bg1.jpg",bg2:"http://39.99.154.145/img/hello/bg2.jpg",bg3:"http://39.99.154.145/img/hello/bg3.jpg",bg4:"http://39.99.154.145/img/hello/bg4.jpg"},isOpen:!1,skinItemStyle:null,skinItemBg:null,isPrimeWindowOpen:!1,changerStyle:{"--hx":1,"--hw":1}}},mounted:function(){var e=this;this.computeWindow();var t=this.$refs.skinWindow;t.className="animeOut";var n=window.localStorage.getItem("theHelloBg");this.bgData=null===n||""===n||void 0===n?{backgroundSize:"cover",backgroundPosition:"center",backgroundImage:'url("'+this.bgImageList.bg1+'")'}:{backgroundSize:"cover",backgroundPosition:"center",backgroundImage:'url("'+n+'")'},window.addEventListener("resize",(function(){e.computeWindow()}))},methods:{skinBg:function(e){return{backgroundImage:'url("'+e+'")'}},computeSkin:function(){var e=this.$refs.bgChangerContent,t=Math.round(.8*e.offsetHeight),n=Math.round(.3*e.offsetWidth);this.skinItemStyle={"--item-width":n+"px","--item-height":t+"px",backgroundSize:"cover",backgroundPosition:"center"}},computeWindow:function(){var e=this.$refs.bgChangerWrapperRef,t=.4*e.offsetHeight,n=.7*e.offsetWidth,i=50/t,r=50/n;this.changerStyle={"--hx":i,"--hw":r}},changeBg:function(){if(this.isOpen)this.closeBgWindow();else{this.isOpen=!0;var e=this.$refs.skinWindow;e.className="animeTo",this.computeWindow(),this.computeSkin()}},closeBgWindow:function(){this.isOpen=!1;var e=this.$refs.skinWindow;e.className="animeOut"},clickToChangeBg:function(e){this.bgData={backgroundSize:"cover",backgroundPosition:"center",backgroundImage:'url("'+e+'")'},window.localStorage.setItem("theHelloBg",e)}}},y=S,k=(n("82ff"),Object(a["a"])(y,w,O,!1,null,"079d6264",null)),_=k.exports,W=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("div",{attrs:{id:"msgBoardWrapper"}},[n("transition",{attrs:{name:"fade"}},[n("div",{directives:[{name:"show",rawName:"v-show",value:e.isOpen,expression:"isOpen"}],attrs:{id:"shade"}})]),n("div",{attrs:{id:"btn"},on:{click:function(t){return e.change(!0)}}},[e._v("留言板")]),n("transition",{attrs:{name:"bigger"}},[n("div",{directives:[{name:"show",rawName:"v-show",value:e.isOpen&&!e.isSending,expression:"isOpen&&!isSending"}],attrs:{id:"wrapper"}},e._l(e.currentMsgData,(function(t){return n("div",{staticClass:"msgBlock"},[n("div",{staticClass:"sends"},[n("div",{staticClass:"sendName"},[e._v(e._s(t.sendName))]),n("div",{staticClass:"sendTime"},[e._v(e._s(e._f("timeFilter")(t.sendTime)))])]),n("div",{staticClass:"content"},[e._v(e._s(t.content))])])})),0)]),n("transition",{attrs:{name:"fade"}},[n("div",{directives:[{name:"show",rawName:"v-show",value:e.isOpen,expression:"isOpen"}],ref:"btnForSendWrapperRef",staticClass:"sendClose",attrs:{id:"btnForSendWrapper"}},[n("div",{attrs:{id:"btnForSend"},on:{click:function(t){e.isSending?e.toSend():e.toSending()}}},[e._v("欢迎留言~")]),n("transition",{attrs:{name:"fade"}},[e.isSending?n("div",{attrs:{id:"formWrapper"}},[e._v(" 输入您的大名 "),n("label",[n("input",{directives:[{name:"model",rawName:"v-model",value:e.currentSendName,expression:"currentSendName"}],attrs:{id:"nameArea",type:"text"},domProps:{value:e.currentSendName},on:{input:function(t){t.target.composing||(e.currentSendName=t.target.value)}}})]),e._v(" 留言 "),n("label",[n("textarea",{directives:[{name:"model",rawName:"v-model",value:e.currentContent,expression:"currentContent"}],attrs:{id:"textArea"},domProps:{value:e.currentContent},on:{input:function(t){t.target.composing||(e.currentContent=t.target.value)}}})])]):e._e()]),n("div",{attrs:{id:"exit"},on:{click:function(t){return e.change(!1)}}},[e._v("轻点退出")])],1)])],1)])},C=[],j=(n("d3b7"),n("bc3a")),x=n.n(j);function N(e){var t=window.document.location.origin,n=x.a.create({baseURL:t,headers:{"content-type":"application/json;charset=UTF-8"}});return n.interceptors.request.use((function(e){return localStorage.getItem("token")&&(e.headers.token=localStorage.getItem("token"),e.headers.contentType="application/json;charset=UTF-8"),e}),(function(e){return Promise.reject(e)})),n.interceptors.response.use((function(e){return e}),(function(e){return 500===e.response.status?Promise.reject("发送登录请求失败 err: 500"):Promise.reject(e.response.status)})),n(e)}function P(){return N({url:"/api/msgBoard/getAll",method:"post"})}function B(e){return N({url:"/api/msgBoard/setOne",method:"post",data:e})}var T=n("d4ec"),I=n("bee2"),M=function(){function e(t,n,i){Object(T["a"])(this,e),this.sendName=t,this.content=n,this.sendTime=i}return Object(I["a"])(e,[{key:"getSendName",get:function(){return this.sendName}},{key:"setSendName",set:function(e){this.sendName=e}},{key:"getContent",get:function(){return this.content}},{key:"setContent",set:function(e){this.content=e}},{key:"getSendTime",get:function(){return this.sendTime}},{key:"setSenTime",set:function(e){this.sendTime=e}}]),e}(),$=function(){function e(){Object(T["a"])(this,e)}return Object(I["a"])(e,null,[{key:"timeFilter",value:function(e){var t=function(e){return 0===e?"01":e<10?"0"+e:e>=10?e:void 0};if(null===e||void 0===e||""===e)return null;var n=new Date(e),i=n.getFullYear(),r=t(n.getMonth()+1),s=t(n.getDate());return i+"-"+r+"-"+s}}]),e}(),D={name:"MsgBoard",data:function(){return{isOpen:!1,isSending:!1,currentMsgData:[],currentSendName:null,currentContent:null,sending:!1}},mounted:function(){var e=this;new M("ahhTou","hello",new Date);P().then((function(t){for(var n=0,i=t.data.length;n<i;n++)e.currentMsgData.push(JSON.parse(t.data[n]))}))},methods:{change:function(e){if(e)this.isOpen=!0,this.isSending=!1;else{var t=this.$refs.btnForSendWrapperRef;this.isSending?(this.isSending=!1,t.className="sendClose"):(t.className="sendClose",this.isOpen=!1,this.isSending=!0)}},toSend:function(){var e=this;if(!this.sending){var t=this.$refs.btnForSendWrapperRef,n=new Date,i=new M(this.currentSendName,this.currentContent,n);""!==i.content&&null!==i.content?B(i).then((function(e){if(e.data)return P()})).then((function(n){e.currentMsgData=[];for(var i=0,r=n.data.length;i<r;i++)e.currentMsgData.push(JSON.parse(n.data[i]));e.isSending=!1,e.sending=!1,t.className="sendClose"})):(this.isSending=!1,t.className="sendClose")}},toSending:function(){var e=this.$refs.btnForSendWrapperRef;e.className="sendOpen",this.isOpen=!0,this.isSending=!0}},filters:{timeFilter:function(e){return $.timeFilter(e)}}},F=D,R=(n("58c8"),Object(a["a"])(F,W,C,!1,null,"117e74bf",null)),E=R.exports,H={name:"Home",components:{PrimeMenu:b,BgChanger:_,MsgBoard:E},data:function(){return{bgImageList:{bg1:"http://39.99.154.145/img/hello/bg1.jpg",bg2:"http://39.99.154.145/img/hello/bg2.jpg",bg3:"http://39.99.154.145/img/hello/bg3.jpg",bg4:"http://39.99.154.145/img/hello/bg4.jpg"},changeBgStyle:{isOpen:!1},isPrimeWindowOpen:!1}},methods:{openPrimeWindow:function(){this.isPrimeWindowOpen=!this.isPrimeWindowOpen}}},z=H,L=(n("5475"),Object(a["a"])(z,d,p,!1,null,"59310714",null)),J=L.exports;i["a"].use(l["a"]);var q=[{path:"/",name:"Home",component:J,meta:{title:"欢迎"}}],A=new l["a"]({mode:"history",base:"hello",routes:q});A.beforeEach((function(e,t,n){document.title=e.meta.title,n()}));var U=A,Y=n("2f62");i["a"].use(Y["a"]);var G=new Y["a"].Store({state:{},mutations:{},actions:{},modules:{}}),K=n("fe3c"),Q=n.n(K);Q.a.prototype.focus=function(e){var t;e.setSelectionRange&&0!==e.type.indexOf("date")&&"time"!==e.type&&"month"!==e.type?(t=e.value.length,e.focus(),e.setSelectionRange(t,t)):e.focus()},Q.a.attach(document.body),i["a"].config.productionTip=!1,new i["a"]({router:U,store:G,render:function(e){return e(u)}}).$mount("#app")},"58c8":function(e,t,n){"use strict";var i=n("42df"),r=n.n(i);r.a},"772a":function(e,t,n){},"82ff":function(e,t,n){"use strict";var i=n("772a"),r=n.n(i);r.a},8468:function(e,t,n){},"85ec":function(e,t,n){},"8ccd":function(e,t,n){},"99ba":function(e,t,n){"use strict";var i=n("8ccd"),r=n.n(i);r.a}});
//# sourceMappingURL=app.a2a28a85.js.map