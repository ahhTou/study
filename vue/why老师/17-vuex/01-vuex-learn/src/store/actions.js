export default {
    aUpdateInfo(content, payload){
      return new Promise((resolve, reject) => {
        setTimeout(()=>{
          content.commit('updatedInfo')
          console.log(payload)
          resolve('Promise返回的信息')
        }, 1000)

      })
    }
}