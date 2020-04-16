<template>
  <div>
    <div id="hatchBoxMax" :class="hatchBoxMax" v-drag="maxBoxOpen">
      <div
        :class="hatchBoxAfter"
        v-on="{mouseenter:panelBiger,mouseleave:panelLitter,dblclick:closeHatchMsgPanel,click:hatchMsgPanel}"
      >
        <div :class="hatchBox" v-bind:title="clickTo + ' ' + username + ' '+ aimsMe "></div>
        <span v-if="isShow" class="hatchMsgBlock">{{ username }}</span>
        <div id="tipsDblclickToClose" v-if="isShow">
          <p>双击任意地方关闭信息面板</p>
        </div>
      </div>
      <transition name="fadePanel">
        <div
          id="hatchPanel"
          v-show="panelShow"
          v-on="{mouseenter:panelBiger,mouseleave:panelLitter}"
          :style="panelIn"
        >
          <p id="username">{{ username }}</p>
          <div v-for="(item, index) in hatchManager" id="listBox" :key="item">
            <a :href="item.id>0?item.url:null">
              <div :class="item.id > 0 ? 'panelList' : 'panelListOut'" :key="index">
                <span class="fontLogo">{{ item.logoFont }}</span>
                <span>{{ item.name }}</span>
              </div>
            </a>
          </div>
        </div>
      </transition>
    </div>
  </div>
</template>


<script>
export default {
  name: "msgwindow.vue",
  data() {
    return {
      aaa: true,
      clickTo: "点击进入",
      aimsMe: "控制面板",
      username: "偷偷呼吸的死肥宅",
      panelIn: {},
      hatchManager: [
        {
          id: 1,
          name: "个人中心",
          url: "www.baidu.com",
          logoFont: "➜"
          // logo:"http://q5hdebxbr.bkt.clouddn.com/bzLogo.svg",
        },
        {
          id: 2,
          name: "追番列表",
          url: "www.bilibili.com",
          logoFont: "★"
          // logo:"http://q5hdebxbr.bkt.clouddn.com/bzLogo.svg",
        },
        {
          id: -1,
          name: "退出",
          logoFont: "✗"
          // logo:"http://q5hdebxbr.bkt.clouddn.com/bzLogo.svg",
        }
      ],
      hatchBox: {
        hatchBox: true,
        hatchBoxOpen: false,
        hatchBoxBiger: false
      },
      hatchBoxAfter: {
        hatchBoxAfter: true,

        hatchBoxAfterOpen: false,
        hatchBoxAfterBiger: false,
        hatchBoxAfterOpenPos: false
      },
      hatchBoxMax: {
        hatchBoxMax: true,
        hatchBoxMaxOpen: false,
        transitionAnm: true
      },
      isRight: true,
      panelShow: false,
      maxBoxOpen: false,
      isShow: false,
      isShowTimeOut: null
    };
  },
  methods: {
    //鼠标监听
    panelBiger: function() {
      if (!this.maxBoxOpen) {
        (this.panelShow = true), (this.hatchBoxAfter.hatchBoxAfterBiger = true);
        this.hatchBox.hatchBoxBiger = true;
      }
    },

    panelLitter: function() {
      if (!this.maxBoxOpen) {
        this.panelShow = false;
        this.hatchBoxAfter.hatchBoxAfterBiger = false;
        this.hatchBox.hatchBoxBiger = false;
      }
    },
    //点击打开面板
    hatchMsgPanel: function() {
      if (this.isShow == false) {
        let that = this;
        this.maxBoxOpen = true;
        (this.isShowTimeOut = setTimeout(function() {
          that.isShow = true;
          that.hatchBoxMax.transitionAnm = false;
          window.clearTimeout(that.isShowTimeOut);
        }, 500)),
          (this.panelShow = false),
          (this.clickTo = "点击切换"),
          (this.aimsMe = "的头像"),
          (this.hatchBoxMax.hatchBoxMaxOpen = true),
          (this.hatchBoxAfter.hatchBoxAfterBiger = false),
          (this.hatchBoxAfter.hatchBoxAfterOpen = true);
        this.hatchBoxAfter.hatchBoxAfterOpenPos = true;
        this.hatchBox.hatchBoxOpen = true;
      }
    },

    //双击关闭面板
    closeHatchMsgPanel: function() {
      let that = this;
      // console.log(maxBoxOpen);
      if (that.maxBoxOpen) {
        that.isShow = false;
        that.maxBoxOpen = false;
        that.clickTo = "点击进入";
        that.aimsMe = "控制面板";
        that.hatchBoxMax.transitionAnm = true;
        (that.isShowTimeOut = setTimeout(function() {
          that.hatchBoxAfter.hatchBoxAfterOpenPos = false;

          window.clearTimeout(that.isShowTimeOut);
        }, 500)),
          //最外部盒子
          (that.hatchBoxMax.hatchBoxMaxOpen = false);

        //面板打开
        that.hatchBoxAfter.hatchBoxAfterBiger = false;
        that.hatchBoxAfter.hatchBoxAfterOpen = false;

        that.hatchBox.hatchBoxOpen = false;
      }
    },

    a1() {
      let that = this;
      console.log("anm===" + that.hatchBoxMax.transitionAnm);
    }
  },

  directives: {
    drag(el, binding) {
      let odiv = el;
      odiv.onmousedown = e => {
        if (binding.value) {
          //算出鼠标相对元素的位置
          let disX = e.clientX - odiv.offsetLeft + 30;
          let disY = e.clientY - odiv.offsetTop + 30;

          document.ondblclick = e => {
            odiv.style.right = 0 + "px";
            odiv.style.top = 0 + "px";
            odiv.style.left = "unset";
          };

          document.onmousemove = e => {
            //用鼠标的位置减去鼠标相对元素的位置，得到元素的位置
            let left = e.clientX - disX;
            let top = e.clientY - disY;

            odiv.style.left = left + "px";
            odiv.style.top = top + "px";
          };

          document.onmouseup = e => {
            document.onmousemove = null;
            document.onmouseup = null;
          };
        }
      };
    }
  }
};
</script>


<style scoped>
a {
  text-decoration: none;
}
.transitionAnm {
  transition: all 0.5s;
}

.transitionAnmNone {
  transition: none;
}
body {
  background: url(https://s2.ax1x.com/2020/02/27/301Q0g.jpg);
}

/* 最外部盒子 */
.hatchBoxMax {
  position: fixed;
  right: 0px;
  top: 0px;
  width: 300px;
  height: 500px;
  display: flex;
  justify-content: center;
  margin: 10px;
}
.hatchBoxMaxOpen {
  width: 1000px;
  height: 600px;
  /* right: unset; */
  margin: 30px;
  justify-content: unset;
}

/* 头像 */
.hatchBox {
  width: 85px;
  height: 85px;
  border-radius: 80px;
  background: url("https://s2.ax1x.com/2020/02/28/3DhbtS.jpg");
  background-size: cover;
  opacity: 0.7;
  /* transition: all 0.5s; */

  margin: 0px auto;
  position: absolute;
}
.hatchBoxBiger {
  opacity: 1;
}
.hatchBoxOpen {
  position: relative;
  opacity: 1;
  border: solid 1px rgba(0, 0, 0, 0.2);
  margin: 20px;
}

/* 头像外层 */
.hatchBoxAfter {
  width: 90px;
  height: 90px;
  border-radius: 100px;

  display: flex;
  justify-content: center;
  align-items: center;

  z-index: 100;
  background: rgba(255, 255, 255, 0);
  background-size: cover;
  transition: all 0.5s;
  transform-origin: 50% 0%;
  transform: scale(1, 1);
  /* transition-timing-function:ease-out; */
  /* transition-timing-function: cubic-bezier(0.31, -0.105, 0.43, 1.59); */
}
.hatchBoxAfterOpen {
  width: 1000px;
  height: 600px;
  background: rgba(255, 255, 255, 0.95);
  border-radius: 50px 20px 20px 20px;
  box-shadow: 2px 2px 5px rgb(138, 138, 138);
}
.hatchBoxAfterOpenPos {
  align-items: unset;
  justify-content: unset;
}
.hatchBoxAfterBiger {
  transform: scale(1.3, 1.3);
  background-color: rgba(255, 255, 255, 1);
}

#hatchPanel {
  /* 250px */
  width: 15.6rem;
  /* height: 400px; */
  background-color: white;
  position: absolute;
  border-radius: 5px;
  top: 75px;
  z-index: 1;
  box-shadow: 2px 2px 5px rgb(138, 138, 138);
  transform-origin: 50% 0%;

  display: inline-block;
  /* align-items: center; */
}

#username {
  width: inherit;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 70px;
  font-size: 15px;
  color: rgb(211, 81, 91);
}
#tipsDblclickToClose {
  width: 1000px;
  height: 50px;
  color: gray;
  font-family: "等线";
  position: absolute;
  display: flex;
  transition: all 0.5s;
  bottom: 0px;
  justify-content: center;
  align-items: center;
  opacity: 1;
  animation: 2s tipsDblclickToClose;
  animation-direction: alternate;
  animation-iteration-count: infinite;
}
@keyframes tipsDblclickToClose {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0.1;
  }
}
#listBox {
  width: inherit;
}

.panelList,
.panelListOut {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 50px;
  color: rgb(51, 51, 51);
  font-size: 1rem;
  margin-top: 1rem;
  margin-bottom: 1rem;
  background: rgba(255, 255, 255, 1);
  display: flex;
  transition: all 0.3s;
  cursor: pointer;
}
/* .panelList img,.panelListOut img{
    transition: all 0.3s;
    opacity: 0;
    width: 1rem;
    height: 1rem;
    transform: translateX(-1rem);
}
.panelList:hover img,.panelListOut:hover img{
    transition: all 0.3s;
    display: inline-block;
    opacity: 1;
    transform: translateX(0rem);

} */
.panelList span,
.panelListOut span {
  transition: all 0.3s;
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  transform: translateX(-0.75rem);
}
.panelList:hover span,
.panelListOut:hover span {
  transform: translateX(0.2rem);
}
.panelList:hover {
  background: rgb(219, 219, 219);
}
.fontLogo {
  transition: all 0.3s;
  opacity: 0;
  font-size: 1.5rem;
  transform: translateX(-1rem);
}
.panelList:hover .fontLogo,
.panelListOut:hover .fontLogo {
  transition: all 0.3s;
  opacity: 1;
  transform: translateX(0rem);
}
.panelListOut:hover {
  transition: color 0s;
  background: rgb(255, 121, 121);
  color: white;
}
.hatchMsgBlock {
  position: relative;
  height: 85px;
  margin-bottom: 20px;
  margin-top: 20px;
  font-size: 30px;
  line-height: 85px;
  color: rgb(211, 81, 91);
}

/* vueCss */
.fadePanel-enter-active,
.fadePanel-leave-active {
  transition: all 0.3s;
}
.fadePanel-enter,
.fadePanel-leave-to {
  opacity: 0;
  transform: scale(1, 0);
}
</style>