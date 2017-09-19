<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../iview/styles/iview.css">
<script src="../iview/iview.min.js" type="text/javascript" ></script>
<script src="../iview/vue.min.js" type="text/javascript" ></script>
</head>
<body>
<div id="app">
    <i-button @click="show">Click me!</i-button>
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
                this.visible = false;
            }
        }
    })
  </script>
</body>
</html>