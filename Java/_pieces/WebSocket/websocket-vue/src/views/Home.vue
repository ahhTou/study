<template>
  <div class="home">
    <h1>{{ status + '到' + username }}</h1>
    <h2>{{ '已发送' + message + '条消息' }}</h2>
    <label>
      用户名
      <input v-model="username" />
    </label>
    <button @click="toConnect">点击连接</button>
    <br />
    <br />
    <label>
      内容
      <input type="text" v-model="newMessage" />
    </label>
    <br />
    <button @click="toSendMsg">发送消息</button>
    <div>
      <p v-for="(li, index) in allMsg" :key="index">{{ li }}</p>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Home',
  data: function() {
    return {
      websocket: null,
      message: 0,
      username: '',
      status: '未连接',
      newMessage: '',
      allMsg: []
    }
  },
  methods: {
    init() {
      console.log('123')
      this.status = '初始化中...'
      this.websocket = new WebSocket(
        'ws://localhost:8081/chat/' + this.username
      )
      this.websocket.onopen = () => {
        this.status = '已连接'
      }
      this.websocket.onmessage = e => {
        this.allMsg.push(e.data)
      }
    },
    toSendMsg() {
      this.websocket.send(this.newMessage)
      this.message++
      this.newMessage = ''
    },
    toConnect() {
      if (this.websocket === null) {
        this.init()
      }
    }
  }
}
</script>
