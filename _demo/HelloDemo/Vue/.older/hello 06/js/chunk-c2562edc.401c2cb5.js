(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-c2562edc"],{"79fc":function(e,n,t){"use strict";t.r(n);var r=function(){var e=this,n=e.$createElement,t=e._self._c||n;return t("shade-slots",{scopedSlots:e._u([{key:"content",fn:function(){return[t("transition",{attrs:{name:"bigger"}},[t("div",{directives:[{name:"show",rawName:"v-show",value:!e.isSending,expression:"!isSending"}],attrs:{id:"wrapperForMsgBoard"}},e._l(e.currentMsgData,(function(n){return t("div",{staticClass:"msgBlock",on:{click:function(e){e.stopPropagation()}}},[t("div",{staticClass:"sends"},[t("div",{staticClass:"sendName"},[e._v(e._s(n.sendName))]),t("div",{staticClass:"sendTime"},[e._v(e._s(e._f("timeFilter")(n.sendTime)))])]),t("div",{staticClass:"content"},[e._v(e._s(n.content))])])})),0)])]},proxy:!0},{key:"controller",fn:function(){return[t("div",{class:e.btnForSendWrapperClassName,attrs:{id:"btnForSendWrapper"}},[t("div",{attrs:{id:"btnForSend"},on:{click:function(n){n.stopPropagation(),e.isSending?e.toSend():e.toSending()}}},[e._v(e._s(e.btnForSendText))]),t("transition",{attrs:{name:"fade"}},[e.isSending?t("div",{attrs:{id:"formWrapper"},on:{click:function(e){e.stopPropagation()}}},[e._v(" 输入您的大名 "),t("label",[t("input",{directives:[{name:"model",rawName:"v-model",value:e.currentSendName,expression:"currentSendName"}],attrs:{id:"nameArea",type:"text"},domProps:{value:e.currentSendName},on:{input:function(n){n.target.composing||(e.currentSendName=n.target.value)}}})]),e._v(" 留言 "),t("label",[t("textarea",{directives:[{name:"model",rawName:"v-model",value:e.currentContent,expression:"currentContent"}],attrs:{id:"textArea"},domProps:{value:e.currentContent},on:{input:function(n){n.target.composing||(e.currentContent=n.target.value)}}})])]):e._e()]),t("div",{attrs:{id:"exit"},on:{click:function(n){return n.stopPropagation(),e.change(!1)}}},[e._v("轻点退出")])],1)]},proxy:!0}])})},i=[],a=(t("96cf"),t("1da1")),s=(t("d3b7"),t("d4ec")),o=t("bee2"),c=t("27fe");function u(){return Object(c["a"])({url:"/api/msgBoard/getAll",method:"post"})}function d(e){return Object(c["a"])({url:"/api/msgBoard/setOne",method:"post",data:e})}var l=function(){function e(n,t,r){Object(s["a"])(this,e),this.sendName=n,this.content=t,this.sendTime=r}return Object(o["a"])(e,[{key:"updateSelf",value:function(){var e=this;return new Promise((function(n,t){d(e).then((function(e){e.data&&n(!0)}))}))}},{key:"getSendName",get:function(){return this.sendName}},{key:"setSendName",set:function(e){this.sendName=e}},{key:"getContent",get:function(){return this.content}},{key:"setContent",set:function(e){this.content=e}},{key:"getSendTime",get:function(){return this.sendTime}},{key:"setSenTime",set:function(e){this.sendTime=e}}],[{key:"allList",value:function(){return new Promise((function(e,n){var t=[];u().then((function(n){for(var r=0,i=n.data.length;r<i;r++)t.push(JSON.parse(n.data[r]));e(t)}))}))}},{key:"setOne",value:function(e){return new Promise((function(n,t){d(e).then((function(e){e.data&&n(!0)}))}))}}]),e}(),f=function(){function e(){Object(s["a"])(this,e)}return Object(o["a"])(e,null,[{key:"timeFilter",value:function(e){var n=function(e){return 0===e?"01":e<10?"0"+e:e>=10?e:void 0};if(null===e||void 0===e||""===e)return null;var t=new Date(e),r=t.getFullYear(),i=n(t.getMonth()+1),a=n(t.getDate());return r+"-"+i+"-"+a}}]),e}(),m=t("867d"),p={name:"MsgBoard",components:{ShadeSlots:m["a"]},data:function(){return{isSending:!1,currentMsgData:[],currentSendName:null,currentContent:null,sendingLock:!1,btnForSendWrapperClassName:"sendClose",btnForSendText:"点击此处开始留言"}},mounted:function(){this.refresh()},methods:{change:function(e){e?this.isSending=!1:this.isSending?(this.isSending=!1,this.closeSend()):this.$router.go(-1)},toSend:function(){var e=this;return Object(a["a"])(regeneratorRuntime.mark((function n(){var t;return regeneratorRuntime.wrap((function(n){while(1)switch(n.prev=n.next){case 0:if(e.sendingLock){n.next=18;break}if(t=new l(e.currentSendName,e.currentContent,new Date),""===t.content||null===t.content){n.next=14;break}return e.btnForSendText="发送中...",n.next=6,t.updateSelf();case 6:if(!n.sent){n.next=12;break}return n.next=9,e.refresh();case 9:e.closeSend(),e.isSending=!1,e.sendingLock=!1;case 12:n.next=16;break;case 14:e.closeSend(),e.isSending=!1;case 16:n.next=19;break;case 18:e.btnForSendText="发送中，请不要重复点击";case 19:case"end":return n.stop()}}),n)})))()},toSending:function(){this.btnForSendWrapperClassName="sendOpen",this.btnForSendText="再次点击进行留言",this.isSending=!0},refresh:function(){var e=this;return Object(a["a"])(regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){while(1)switch(n.prev=n.next){case 0:return n.next=2,l.allList();case 2:e.currentMsgData=n.sent;case 3:case"end":return n.stop()}}),n)})))()},closeSend:function(){var e=this;this.$nextTick((function(){e.btnForSendWrapperClassName="sendClose",e.btnForSendText="点击此处开始留言"}))}},filters:{timeFilter:function(e){return f.timeFilter(e)}}},g=p,v=(t("92d4"),t("2877")),h=Object(v["a"])(g,r,i,!1,null,"5f0453b0",null);n["default"]=h.exports},"92d4":function(e,n,t){"use strict";var r=t("fe3e"),i=t.n(r);i.a},fe3e:function(e,n,t){}}]);
//# sourceMappingURL=chunk-c2562edc.401c2cb5.js.map