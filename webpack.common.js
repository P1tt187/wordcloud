const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const webpack = require('webpack');
module.exports = {
    entry: {
    cloud :'./assets/js/cloud.js',
        cloudStyle: './assets/scss/cloud.scss'
    },
    output: {
        filename: '[name].js',
        path: path.resolve(__dirname, 'src/main/resources/static'),
        publicPath: '/'
    },
    resolve: {
        extensions: ['.js', '.jsx'],
        fallback: {
            "querystring": require.resolve("querystring-es3")
        }
    },
    module: {
        rules: [{
            test: /\.(jsx|js)$/,
            exclude: /(node_modules)/,
            use: [{
                loader: 'babel-loader'
            }]
        },
            //bootstrap and bootbox need jQuery to be available, so make it available with the imports loader
            {
                test: /bootstrap.+\.(jsx|js)$/,
                use: [
                    {
                        loader: 'imports-loader',
                        options: {
                            imports: [
                                "default jquery $",
                            ]
                        }
                    }]
            },
            {
                test: /\.(png|bmp|jpg|jpeg)$/,
                include: /(images)/,
                use: {
                    loader: 'file-loader'
                }
            },
            {
                test: /\.(jpg|jpeg|png|woff|woff2|eot|ttf|svg|bmp)$/,
                exclude: /(images)/,
                use: [{
                    loader: 'url-loader?limit=1024'
                }]
            },
            {
                test: /\.(sa|sc|c)ss$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    {loader: 'css-loader'},
                    {
                        loader: 'postcss-loader',
                        options: {
                            postcssOptions: {
                                plugins: function () { // post css plugins, can be exported to postcss.config.js
                                    return [
                                    //    require('precss'),
                                        require('autoprefixer')
                                    ];
                                }
                            },
                        },
                    },
                    {
                        loader: 'sass-loader',
                    },
                ],
            }
        ]
    },

    plugins: [
        new webpack.ProvidePlugin({
            'jQuery': 'jquery',
            'window.jQuery': 'jquery',
            'jquery': 'jquery',
            'window.jquery': 'jquery',
            '$': 'jquery',
            'window.$': 'jquery',
            'moment': 'moment',
        }),
        // Extracts CSS into separate files
        new MiniCssExtractPlugin({
            filename: "[name].css",
            chunkFilename: "[id].css"
        }),
    ]
};
