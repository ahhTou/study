import ImgWithText from '@/assets/ImgWithText'

export default class Text {
  static titleMin = '小笨喵'
  static titleMain = '福福'

  // 主标题的颜色
  static color_title_Main = 'rgb(198,237,184)'

  // 每张图片的配字颜色
  static color_every_title = 'rgb(255,255,255)'
  static isBolder = true
  static font_size_every_title = '30px'

  // 图片 与 配字
  static size = 4
  static list = {
    1: new ImgWithText('冷酷', '/img/1.webp', 'large'),
    2: new ImgWithText('帅呆', '/img/2.webp', 'min1'),
    3: new ImgWithText('傻福福', '/img/3.webp', 'min2'),
    4: new ImgWithText('懵喵', '/img/4.webp', 'large')
  }
}
