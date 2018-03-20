/**
 * Created by Administrator on 2017/4/10.
 */

function navConfig() {}
navConfig.prototype.nav=[];
var ymzs=new navConfig();
ymzs.nav=[];
ymzs.url=[];
function getUrl(arr){
  if(arr.length==0){
    return [];
  }
  arr.forEach(function(value, index, array) {
     if(value.url!=null && value.url!=""){
       ymzs.url.push(value.url);
     }
    if(value.children!=null&&value.children.length>0){
      getUrl(value.children);
    }
  })
  return ymzs.url;
}
export {ymzs,getUrl}
