<template>
  <div id="hello-banner-style-x-wrapper">
    <div class="banner-blank" :style="style" v-for="(k, i) in list" :class="className[i]" :key="i"
         @click="toFullScreen(i, $event)">
      <div class="block-cover" :style="{backgroundImage: 'url('  + k.bg + ') ', backgroundPosition: 'top center'}">
        <p>{{ k.title }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HelloBannerStyleX',
  data: function () {
    return {
      list: [{
        bg: '/img/1.webp',
        title: '小腹',
      }, {
        bg: 'img/2.webp',
        title: '大腿'
      }, {
        bg: 'img/3.webp',
        title: '奶子'
      }],
      className: [],
      right: 0,
      top: 0,
      clickFlag: true,
      timer: 1,
    }
  },
  computed: {
    style() {
      return {
        '--left': this.right * -1 + 'px',
        '--top': this.top * -1 + 'px',
        '--ani-timer': this.timer + 's',
      }
    }
  },
  mounted() {
    this.list.forEach(() => {
      this.className.push([])
    })
  },
  methods: {
    toFullScreen(i, e) {

      let name = 'fullScreen'
      if (this.className[i].isNotInclude(name)) {
        this.clickFlag = false

        this.className[i].push(name)
        let dom = e.currentTarget
        this.right = dom.offsetLeft
        this.top = dom.offsetTop
        // 左右设置
        for (let j = 0; j < this.className.length; j++) {
          if (j < i) {
            this.className[j].push('to-left')
          } else if (j > i) {
            this.className[j].push('to-right')
          }
        }
      } else {
        this.className[i].remove(name)
        document.body.style.overflow = ''
        this.className.forEach(n => {
          n.remove('to-left')
          n.remove('to-right')
        })
      }

    }
  }


}
</script>

<style lang="scss" scoped>
#hello-banner-style-x-wrapper {
  position: relative;
  --left: 0;
  --top: 0;
  --ani-timer: 1s;
  height: 100vh;
  width: 100vw;
  overflow: hidden;
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;


  $h: 500px;
  $w: 500px;

  .banner-blank {
    display: block;
    flex-shrink: 0;
    position: relative;
    cursor: pointer;
    width: $w;
    height: $h;
    margin: 50px;
    border-radius: 30px;
    background: rgb(24, 24, 24);
    box-shadow: 20px 20px 40px rgba(0, 0, 0, .2);
    transition: all var(--ani-timer);
    overflow: hidden;

    .block-cover {
      position: relative;
      width: $w;
      height: $h;
      border-radius: 30px;
      transition: background-size 2s;
      background-size: 120%;

      p {
        padding: 20px;
        user-select: none;
        font-size: 80px;
        font-weight: bolder;
        color: rgb(245, 245, 247);
        position: absolute;
        bottom: 0;
        left: 0;
        text-shadow: 0 0 10px rgba(0, 0, 0, .5);
      }
    }
  }

  .fullScreen {
    width: 100vw;
    height: 100vh;

    padding: 100px;

    border-radius: 0;
    transform: translate(var(--left), var(--top));
    z-index: 2;

    .block-cover {
      background-size: 100%;
    }

  }

  .to-left {
    transform: translateX(-2*$h) translateY(-300px) scale(.5);
    opacity: .8;

    .block-cover {
      background-size: 100%;
    }

  }

  .to-right {
    transform: scale(.5) translateY(-300px);
    opacity: .8;

    .block-cover {
      background-size: 100%;
    }
  }

}
</style>
