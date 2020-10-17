import MyAxios from 'assets/utils/MyAjax'

let myAxios = new MyAxios()
myAxios.addInterceptorsForRequest(config => {
    console.log(config)
})