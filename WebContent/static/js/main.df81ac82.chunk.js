(this.webpackJsonpclient=this.webpackJsonpclient||[]).push([[0],{19:function(e,t,n){e.exports=n(34)},24:function(e,t,n){},25:function(e,t,n){},31:function(e,t,n){},33:function(e,t,n){},34:function(e,t,n){"use strict";n.r(t);var r=n(0),a=n.n(r),i=n(5),l=n.n(i),s=(n(24),n(6)),o=n(7),c=n(16),u=n(8),f=n(17),h=(n(25),function(e){var t=e.label,n=e.value,r=e.handleOnChange,i=e.handleKeyPress;return a.a.createElement("div",{className:"url-input"},a.a.createElement("label",{htmlFor:"url-input"},t),a.a.createElement("input",{id:"url-input",value:n,onChange:function(e){return r(e.target.value)},onKeyPress:function(e){return i(e)}}))}),d=n(9);function m(){var e=Object(d.a)(["\n    margin: 10px auto;\n    padding: 8px 15px;\n    background-color: #6495ed;\n    color: #ffffff;\n    font-size: 1em;\n    border: 1px solid #6495ed;\n    border-radius: 2px;\n"]);return m=function(){return e},e}var p=n(10).a.button(m()),y=function(e){var t=e.name,n=(e.url,e.handleTinify);return a.a.createElement(p,{name:t,onClick:n},t)},g=n(14),v=(n(31),function(e){var t=e.message,n=e.tinifiedUrl,r=e.href,i=n?"success-result":"failed-result";return a.a.createElement(a.a.Fragment,null,a.a.createElement("div",{className:"result ".concat(i)},a.a.createElement("div",null,t),a.a.createElement("a",{href:r},n)),function(){if("success-result"===i)return a.a.createElement(a.a.Fragment,null,a.a.createElement(g.CopyToClipboard,{text:n},a.a.createElement(p,null,"Copy URL to Clipboard")))}())}),b=n(3),E=n.n(b),U=n(15);function w(){return(w=Object(U.a)(E.a.mark((function e(){var t,n,r,a=arguments;return E.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return t=a.length>0&&void 0!==a[0]?a[0]:"",n=a.length>1&&void 0!==a[1]?a[1]:{},e.next=4,fetch(t,{method:"POST",mode:"cors",cache:"no-cache",credentials:"same-origin",headers:{"Content-Type":"application/json"},redirect:"follow",referrerPolicy:"no-referrer",body:JSON.stringify(n)});case 4:return r=e.sent,e.next=7,r.json();case 7:return e.abrupt("return",e.sent);case 8:case"end":return e.stop()}}),e)})))).apply(this,arguments)}var T=function(){return w.apply(this,arguments)},C=(n(33),function(e){function t(e){var n;return Object(s.a)(this,t),(n=Object(c.a)(this,Object(u.a)(t).call(this,e))).handleUrlInput=function(e){n.setState({url:e})},n.handleKeyPress=function(e){"Enter"===e.key&&n.fetchTinyUrl()},n.fetchTinyUrl=function(){n.state.url?T("http://localhost:8080/api/tiny/genTinyLink",{url:n.state.url}).then((function(e){"INVALID"===e.status?n.setState({message:"Invalid URL !!",tinyUrl:"",href:""}):"ACTIVE"===e.status&&n.setState({tinyUrl:e.tinyUrl,href:e.tinyUrl,message:"Tinified Successfully !!"})})):n.setState({message:"No URL found !!",tinyUrl:"",href:""})},n._showResult=function(){return""!==n.state.message?a.a.createElement(v,{href:n.state.href,tinifiedUrl:n.state.tinyUrl,message:n.state.message}):""},n.state={url:"",tinyUrl:"",href:"",message:""},n}return Object(f.a)(t,e),Object(o.a)(t,[{key:"render",value:function(){return a.a.createElement("div",{className:"wrapper"},a.a.createElement(h,{label:"Enter URL: ",value:this.state.url,handleOnChange:this.handleUrlInput,handleKeyPress:this.handleKeyPress}),a.a.createElement(y,{name:"Tinify",url:this.state.url,handleTinify:this.fetchTinyUrl}),this._showResult())}}]),t}(a.a.Component));Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));l.a.render(a.a.createElement(C,null),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then((function(e){e.unregister()}))}},[[19,1,2]]]);
//# sourceMappingURL=main.df81ac82.chunk.js.map