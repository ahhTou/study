const path = require('path')
const webpack = require('webpack')
const htmlWebpackPlugin = require('html-webpack-plugin')
const uglifyjsWebpackPlugin = require('uglifyjs-webpack-plugin')

module.exports = {
    entry: './src/main.js',
    output: {
        path: path.resolve(__dirname, '../dist'),
        filename: 'bundle.js',
        // //配置打包后路径
        // publicPath: 'dist/'
    },
    module: {
        rules: [
            //css
            {
                test: /\.css$/,
                //使用时，从右到左
                use: ['style-loader', 'css-loader']
            },

            //less
            {
                test: /\.less$/,
                use: [{
                    loader: "style-loader" // creates style nodes from JS strings
                }, {
                    loader: "css-loader" // translates CSS into CommonJS
                }, {
                    loader: "less-loader" // compiles Less to CSS
                }]
            },

            //url图片
            {
                test: /\.(png|jpg|gif|jpeg)$/,
                use: [
                    {
                      loader: 'url-loader',
                      options: {
                          //小于这个限制，会变成base64
                          //大于这个限制会使用模块 file-loader
                        limit: 2000,
                        name:'img/[name].[hash:8].[ext]'
                      },

                    }
                  ]
            },
            
            //es6转 es5
            {
                test: /\.js$/,
                //排除
                exclude: /(node_modules|bower_components)/,
                use: {
                    loader: 'babel-loader',
                    options: {
                      presets: ['es2015']
                    }
                  }
            },

            {
                test:/\.vue$/,
                use:['vue-loader']
            }
        ]
    },
    resolve:{
        //扩展名
        extensions:['.js', '.css', '.vue'],
        //别名
        alias:{
            'vue$':'vue/dist/vue.esm.js'
        }
    },
    plugins:[
        new webpack.BannerPlugin('最终版权归aaa所有'),

        new htmlWebpackPlugin({
            template:'index.html'
        }),

    ],
}