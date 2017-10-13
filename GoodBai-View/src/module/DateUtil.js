/**
 * Created by John on 2017/10/12.
 */
const formatDate = (date, fmt) => {
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
  }
  let o = {
    'M+': date.getMonth() + 1,
    'd+': date.getDate(),
    'h+': date.getHours(),
    'm+': date.getMinutes(),
    's+': date.getSeconds()
  };
  for (let k in o) {
    if (new RegExp(`(${k})`).test(fmt)) {
      let str = o[k] + '';
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : padLeftZero(str));
    }
  }
  return fmt;
}


function padLeftZero(str) {
  return ('00' + str).substr(str.length);
}

const formatDateLong = (dateLong, fmt) => {
  var date = new Date(dateLong);
  return formatDate(date,fmt);
}


export {formatDate,formatDateLong}

