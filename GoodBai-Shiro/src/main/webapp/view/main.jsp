<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../iview/styles/iview.css">
<script src="../iview/vue.min.js" type="text/javascript" ></script>
<script src="../iview/iview.min.js" type="text/javascript" ></script>
</head>
<body>
<div id="app">
    <i-button @click="show">Clickxx me!</i-button>
    <Modal v-model="visible" title="Welcome">欢迎使用 iView</Modal>
</div>
<script>
    new Vue({
        el: '#app',
        data: {
            visible: false
        },
        methods: {
            show: function () {
                this.visible = true;
            }
        }
    })
  </script>
</body>
</html>