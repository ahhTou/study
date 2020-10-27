import MyAxios from '@/utils/MyAjax'

let myAxios = new MyAxios()
myAxios.addInterceptorsForRequest(config => {
    console.log(config)
})
