(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4840bb4e"],{"1feb":function(e,t,i){"use strict";i.r(t);var n=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("shade-slots",{scopedSlots:e._u([{key:"card",fn:function(t){return[i("div",{attrs:{id:"register-wrapper"},on:{click:function(e){e.stopPropagation()}}},[i("div",{staticClass:"block",style:e.inputTitleStyle,attrs:{id:"main-title"}},[i("div",{directives:[{name:"show",rawName:"v-show",value:!e.isErrTips,expression:"!isErrTips"}],staticStyle:{"font-size":"30px","font-weight":"bolder"}},[e._v(" "+e._s(e.status[e.step-1].title)+" ")]),i("div",{directives:[{name:"show",rawName:"v-show",value:!e.isErrTips,expression:"!isErrTips"}],staticStyle:{margin:"10px",color:"rgb(142, 142, 146)"}},[e._v(" "+e._s(e.status[e.step-1].describe)+" ")]),i("div",{directives:[{name:"show",rawName:"v-show",value:e.isErrTips,expression:"isErrTips"}],staticStyle:{color:"indianred","font-size":"40px","font-weight":"bolder"}},[e._v(" "+e._s(e.errTips)+" ")])]),i("div",{staticClass:"block",attrs:{id:"inputLine"}},[i("div",{style:e.inputStyle,attrs:{id:"long-line"}},e._l(e.status,(function(t){return i("div",{staticClass:"inputBox"},[i("label",["checkbox"===t.inputType?i("input",{directives:[{name:"model",rawName:"v-model",value:e.USER[t.type],expression:"USER[item.type]"}],attrs:{placeholder:t.placeholder,type:"checkbox"},domProps:{checked:Array.isArray(e.USER[t.type])?e._i(e.USER[t.type],null)>-1:e.USER[t.type]},on:{change:function(i){var n=e.USER[t.type],r=i.target,a=!!r.checked;if(Array.isArray(n)){var s=null,c=e._i(n,s);r.checked?c<0&&e.$set(e.USER,t.type,n.concat([s])):c>-1&&e.$set(e.USER,t.type,n.slice(0,c).concat(n.slice(c+1)))}else e.$set(e.USER,t.type,a)}}}):"radio"===t.inputType?i("input",{directives:[{name:"model",rawName:"v-model",value:e.USER[t.type],expression:"USER[item.type]"}],attrs:{placeholder:t.placeholder,type:"radio"},domProps:{checked:e._q(e.USER[t.type],null)},on:{change:function(i){return e.$set(e.USER,t.type,null)}}}):i("input",{directives:[{name:"model",rawName:"v-model",value:e.USER[t.type],expression:"USER[item.type]"}],attrs:{placeholder:t.placeholder,type:t.inputType},domProps:{value:e.USER[t.type]},on:{input:function(i){i.target.composing||e.$set(e.USER,t.type,i.target.value)}}})])])})),0)]),i("div",{staticClass:"block",attrs:{id:"btnLine"}},[i("span",{on:{click:function(t){return e.clickController(!0)}}},[i("btn-circle",{attrs:{"is-active":e.nextBtnIsActive,"is-loading":e.isLoading}})],1)])])]}}])})},r=[],a=(i("d3b7"),i("25f0"),i("96cf"),i("1da1")),s=i("867d"),c=i("b59b"),o=(i("ac1f"),i("466d"),i("498a"),i("d4ec")),l=i("ade3"),u=function e(){Object(o["a"])(this,e)};Object(l["a"])(u,"emailRegx","^[A-Za-z0-9一-龥]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$"),Object(l["a"])(u,"usernameRegx","^[a-zA-Z0-9_-一-龥]{4,16}$"),Object(l["a"])(u,"passwordRegx","^.*(?=.{6,})(?=.*\\d)(?=.*[a-z]).*$"),Object(l["a"])(u,"checkEmail",(function(e){if(null!=e){e=e.trim();var t=e.match(u.emailRegx);return null!==t}return!1})),Object(l["a"])(u,"checkUsername",(function(e){if(null!=e){e=e.trim();var t=e.match(u.usernameRegx);return null!==t}return!1})),Object(l["a"])(u,"checkPassword",(function(e){if(null!=e){e=e.trim();var t=e.match(u.passwordRegx);return null!==t}return!1}));var p=i("e224"),d={name:"Register",components:{BtnCircle:c["a"],ShadeSlots:s["a"]},data:function(){return{USER:new p["a"],inputTitleStyle:{opacity:"1",transition:"opacity .1s"},step:1,vars:"username",isLoading:!1,isErrTips:!1,errTips:null,status:[{title:"输入邮箱",describe:"输入正确的邮箱并接受验证码以完成验证",placeholder:"请输入邮箱",data:null,already:!1,type:"email",inputType:"text"},{title:"输入验证码",describe:"输入正确以继续注册",placeholder:"请输入六位验证码",data:null,already:!1,type:"code",inputType:"text"},{title:"用户名",describe:"4-16位,可以使用中文、字母、数字、下划线、减号）",placeholder:"输入用户名",data:null,already:!1,type:"username",inputType:"text"},{title:"首次输入密码",describe:"至少6位，必须是字母+数字的组合",placeholder:"请输入密码",data:null,already:!1,type:"password",inputType:"password"},{title:"再次输入密码",describe:"密码要保证和上一次一样",placeholder:"请再次输入密码",data:null,type:"passwordR",already:!1,inputType:"password"}]}},computed:{inputStyle:function(){return{transition:"all .2s",opacity:this.step,transform:"translateX(-"+100*(this.step-1)+"vw)","--length":this.status.length}},isMaxStep:function(){return this.step===this.status.length+1},nextBtnIsActive:function(){var e=!1;switch(this.step){case 1:e=u.checkEmail(this.USER.email);break;case 2:e=null!==this.USER.code&&6===this.USER.code.toString().length;break;case 3:e=u.checkUsername(this.USER.username);break;case 4:e=u.checkPassword(this.USER.password);break;case 5:e=this.USER.password===this.USER.code}return e}},methods:{toRegSucView:function(){this.$children[0].exit(),this.$myTipsViews("open",{type:"regSuc",data:this.USER.username})},btnToLoading:function(){this.isLoading=!0},btnNotLoading:function(){this.isLoading=!1},clickController:function(e){e&&this.step<=this.status.length&&this.nextBtnIsActive?this.toReq():!e&&this.step>1&&this.stepController("incr")},toReq:function(){switch(this.step){case 1:this.funcWorker(this.USER.sendVerificationEmail,"邮箱已存在");break;case 2:this.funcWorker(this.USER.checkVerification,"验证码错误");break;case 3:this.funcWorker(this.USER.checkUsernameUnique,"用户名已存在");break;case 4:this.stepController("incr");break;case 5:this.funcWorker(this.USER.register,"注册失败");break}},funcWorker:function(e,t){var i=this;return Object(a["a"])(regeneratorRuntime.mark((function n(){var r,a,s;return regeneratorRuntime.wrap((function(n){while(1)switch(n.prev=n.next){case 0:if(console.log(i.USER),r=i.step-1,a=i.status[r],a.already){n.next=21;break}return i.btnToLoading(),n.next=7,e();case 7:s=n.sent,n.t0=s,n.next=!0===n.t0?11:16;break;case 11:return a.already=!0,console.log("前进"),i.btnNotLoading(),i.stepController("incr"),n.abrupt("break",19);case 16:return i.btnNotLoading(),i.stepController("err",null!==s?s:t),n.abrupt("break",19);case 19:n.next=22;break;case 21:i.stepController("incr");case 22:case"end":return n.stop()}}),n)})))()},stepController:function(e,t){switch(e){case"incr":this.step++,this.isMaxStep?this.toRegSucView():this.nextInputView();break;case"decr":this.step--;break;case"err":this.changeTitle("err",t);break;default:break}},inputText:function(){var e=this,t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"normal",i=arguments.length>1&&void 0!==arguments[1]?arguments[1]:0,n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null;return new Promise((function(r){setTimeout((function(){switch(t){case"err":e.isErrTips=!0,e.errTips=n,e.inputTitleStyle.opacity="1";break;case"normal":e.isErrTips=!1,e.errTips=null,e.inputTitleStyle.opacity="1";break;case"opacity":e.inputTitleStyle.opacity="0";break}r()}),i)}))},nextInputView:function(){this.changeTitle("incr"),this.isLoading=!1},changeTitle:function(e,t){var i=this,n=100;switch(this.inputText("opacity"),e){case"incr":this.inputText("opacity",n).then((function(){i.inputText("normal",n)}));break;case"decr":this.inputText("opacity",n).then((function(){i.inputText("normal",n)}));break;case"err":this.inputText("err",n,t).then((function(){return i.inputText("opacity",3e3)})).then((function(){i.inputText("normal",n)}))}}}},h=d,f=(i("b7b2"),i("2877")),m=Object(f["a"])(h,n,r,!1,null,"56d8a50d",null);t["default"]=m.exports},"25f0":function(e,t,i){"use strict";var n=i("6eeb"),r=i("825a"),a=i("d039"),s=i("ad6d"),c="toString",o=RegExp.prototype,l=o[c],u=a((function(){return"/a/b"!=l.call({source:"a",flags:"b"})})),p=l.name!=c;(u||p)&&n(RegExp.prototype,c,(function(){var e=r(this),t=String(e.source),i=e.flags,n=String(void 0===i&&e instanceof RegExp&&!("flags"in o)?s.call(e):i);return"/"+t+"/"+n}),{unsafe:!0})},"498a":function(e,t,i){"use strict";var n=i("23e7"),r=i("58a8").trim,a=i("c8d2");n({target:"String",proto:!0,forced:a("trim")},{trim:function(){return r(this)}})},5899:function(e,t){e.exports="\t\n\v\f\r                　\u2028\u2029\ufeff"},"58a8":function(e,t,i){var n=i("1d80"),r=i("5899"),a="["+r+"]",s=RegExp("^"+a+a+"*"),c=RegExp(a+a+"*$"),o=function(e){return function(t){var i=String(n(t));return 1&e&&(i=i.replace(s,"")),2&e&&(i=i.replace(c,"")),i}};e.exports={start:o(1),end:o(2),trim:o(3)}},b7b2:function(e,t,i){"use strict";var n=i("ece2"),r=i.n(n);r.a},c8d2:function(e,t,i){var n=i("d039"),r=i("5899"),a="​᠎";e.exports=function(e){return n((function(){return!!r[e]()||a[e]()!=a||r[e].name!==e}))}},ece2:function(e,t,i){}}]);
//# sourceMappingURL=chunk-4840bb4e.5fa74b7b.js.map