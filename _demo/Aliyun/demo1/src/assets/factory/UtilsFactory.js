import MyAxios from 'assets/utils/axios'

export default class UtilsFactory {

    static myAxios

    static create = () => {
        this.myAxios = new MyAxios()
    }

    static getAxios = () => {
        return UtilsFactory.myAxios
    }


}