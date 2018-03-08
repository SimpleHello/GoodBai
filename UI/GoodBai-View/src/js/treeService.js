scloudApp.service('treeService', ['$rootScope', '$http','fsuService', function ($rootScope, $http, fsuService) {
    var setting = {
        rootCode: -1,
        //简单树的配置信息
        simple: {
            view: {
                showIcon: false,
                showLine: false,
                showTitle: false
            },
            data: {
                key: {
                    children: 'nextTier',
                    name: 'name',
                    title: 'code'
                }
            }
        },
        //复选树的配置信息
        checkbox: {
            check: {
                enable: true,
                chkStyle: 'checkbox',
                chkboxType: {
                    'Y': 'ps',
                    'N': 'ps'
                }
            },
            view: {
                showIcon: false,
                showLine: false,
                showTitle: false
            },
            data: {
                key: {
                    children: 'nextTier',
                    name: 'name',
                    title: 'code'
                }
            },
            callback: {
                onClick: function (event, treeId, treeNode) {
                    if (treeNode.nocheck === false) {
                        //如果这个节点是可选的，单击文字的时候将选中此项
                        var treeObj = $.fn.zTree.getZTreeObj(treeId);
                        treeObj.checkNode(treeNode, !treeNode.checked, true, true);
                    }
                }
            }
        },
        //单选树的配置信息
        radio: {
            check: {
                enable: true,
                chkStyle: 'radio',
                chkboxType: {
                    'Y': 'ps',
                    'N': 'ps'
                },
                radioType: 'all'
            },
            view: {
                showIcon: false,
                showLine: false,
                showTitle: false
            },
            data: {
                key: {
                    children: 'nextTier',
                    name: 'name',
                    title: 'code'
                }
            },
            callback: {
                onClick: function (event, treeId, treeNode) {
                    if (treeNode.nocheck === false) {
                        //如果这个节点是可选的，单击文字的时候将选中此项
                        var treeObj = $.fn.zTree.getZTreeObj(treeId);
                        treeObj.checkNode(treeNode, true, true, true);
                    }
                }
            }
        },
        // 单选站点树（带状态）
        radioState: {
            check: {
                enable: true,
                chkStyle: 'radio',
                chkboxType: {
                    'Y': 'ps',
                    'N': 'ps'
                },
                radioType: 'all'
            },
            view: {
                showIcon: false,
                showLine: false,
                showTitle: false,
                addDiyDom: function (treeId, treeNode) {
                    if(!treeNode.id){
                        return;
                    }
                    var aObj = $("#" + treeNode.tId + "_a");
                    var editStr = "";
                    if(treeNode.fsuState){
                        editStr = "<i class='ic ic-circle font-success' title='在线'></i>";
                    }else{
                        editStr = "<i class='ic ic-circle font-default' title='离线'></i>";
                    }
                    aObj.prepend(editStr);
                }
            },
            data: {
                key: {
                    children: 'nextTier',
                    name: 'name',
                    title: 'code'
                }
            },
            callback: {
                onClick: function (event, treeId, treeNode) {
                    if (treeNode.nocheck === false) {
                        //如果这个节点是可选的，单击文字的时候将选中此项
                        var treeObj = $.fn.zTree.getZTreeObj(treeId);
                        treeObj.checkNode(treeNode, true, true, true);
                    }
                }
            }
        },
        //复选权限树
        checkboxPrivilege: {
            check: {
                enable: true,
                chkStyle: 'checkbox',
                chkboxType: {
                    'Y': 'ps',
                    'N': 'ps'
                }
            },
            view: {
                showIcon: false,
                showLine: false,
                showTitle: false
            },
            data: {
                key: {
                    children: 'children',
                    name: 'name',
                    title: 'code'
                }
            },
            callback: {
                onClick: function (event, treeId, treeNode) {
                    if (treeNode.nocheck === false) {
                        //如果这个节点是可选的，单击文字的时候将选中此项
                        var treeObj = $.fn.zTree.getZTreeObj(treeId);
                        treeObj.checkNode(treeNode, !treeNode.checked, true, true);
                    }
                }
            }
        }
    };

    //去除非叶子节点前的单选框
    function cancelParentNodeRadio(parentNode) {
        if (parentNode.nextTier) {
            parentNode.nocheck = true;
            var tierList = parentNode.nextTier;
            for (var i = 0; i < tierList.length; i++) {
                cancelParentNodeRadio(tierList[i]);
            }
        }
    }

    //去除非站点节点前的单/多选框,该树是带站点树
    function cancelParentNodeCheckBySiteLeaf(parentNode) {
        if (parentNode.nextTier) {
            parentNode.nocheck = true;
            parentNode.end = false;
            var tierList = parentNode.nextTier;
            for (var i = 0; i < tierList.length; i++) {
                tierList[i] && cancelParentNodeCheckBySiteLeaf(tierList[i]);
            }
        } else {
            parentNode.end = true;
            if (!parentNode.id) {
                parentNode.nocheck = true;
            }
        }
    }

    //对树进行递归搜索
    function searchInRecursion(data, key) {
        var result = {};
        if (data.nextTier !== null) { // 如果节点有子树
            result.name = data.name;
            result.code = data.code;
            result.id = data.id;
            result.end = data.end;
            if (data.name.indexOf(key) > -1) { // 如果属性匹配，则本节点及其子树全部保留
                result.nextTier = data.nextTier;
                result.open = false;
            } else { // 如果属性不匹配，再遍历其子树上的节点，将匹配的节点加入result.nextTier[]中
                result.nextTier = [];
                var j = 0;
                if(data.nextTier && data.nextTier.length !== 0){
                    for (var i = 0 ; i < data.nextTier.length ; i++) {
                        if (data.nextTier[i]) {
                            var temp = searchInRecursion(data.nextTier[i], key);
                            if (temp.name) {
                                result.nextTier[j++] = temp;
                            }
                        }
                    }
                }
                if (result.nextTier.length > 0) {
                    result.open = true;
                } else {
                    result.name = null;
                }
            }
        } else { // 如果是叶子节点
            if (data.name.indexOf(key) > -1) { // 如果属性匹配
                result = data;
            } else { // 如果属性不匹配
                result.name = null;
            }
        }
        return result;
    }

    //展开节点
    function openNode(node, deep) {
        if (deep < 1 || node.end === true) {
            return;
        }

        node.open = true;
        var nextTier = node.nextTier;
        for (var i = 0; i < nextTier.length; i++) {
            openNode(nextTier[i], deep - 1);
        }
    }

    //展开全部节点
    function openAllNode(node) {
        if (node.end === true || !node.nextTier) {
            return;
        }

        node.open = true;
        var nextTier = node.nextTier;
        for (var i = 0; i < nextTier.length; i++) {
            openAllNode(nextTier[i]);
        }
    }
    //展开全部节点（权限树）
    function openAllNodeByChildren(node) {
        if(!node.children){
            return;
        }
        node.open = true;
        var children = node.children;
        for (var i = 0; i < children.length; i++) {
            openAllNodeByChildren(children[i]);
        }
    }

    var service = {
        //获取有站点的层级的层级树
        getTierTree: function (uniqueCode, success, error) {
            if (!uniqueCode) {
                uniqueCode = "必要但无意义参数";
            }
            $http.post(ctx + "/siteFunc/getTierTree", uniqueCode).success(function (data) {
                if (data.success === false) {
                    $rootScope.notify({
                        type: 'warn',
                        text: '站点分级树获取失败'
                    });
                    return;
                }
                if (!data.data) {
                    error && error();
                    return;
                }

                success({
                    code: setting.rootCode,
                    name: "全部",
                    end: false,
                    nextTier: data.data.firstTier
                });
            }).error(function () {
                console.error("站点分级树获取请求失败！");
            });
        },
        //获取管辖区域内的所有层级的层级树
        getUserTierTree: function (uniqueCode, success, error) {
            if (!uniqueCode) {
                uniqueCode = "必要但无意义参数";
            }
            $http.post(ctx + "/siteFunc/getUserTierTree", uniqueCode).success(function (data) {
                if (data.success === false) {
                    $rootScope.notify({
                        type: 'warn',
                        text: '站点分级树获取失败'
                    });
                    return;
                }
                if (!data.data) {
                    error && error();
                    return;
                }

                success({
                    code: setting.rootCode,
                    name: "全部",
                    end: false,
                    nextTier: data.data.firstTier
                });
            }).error(function () {
                console.error("站点分级树获取请求失败！");
            });
        },
        //获取全部层级的层级树
        getAllTierTree: function (uniqueCode, success, error) {
            if (!uniqueCode) {
                uniqueCode = "必要但无意义参数";
            }
            $http.post(ctx + "/siteFunc/getAllTierTree", uniqueCode).success(function (data) {
                if (data.success === false) {
                    $rootScope.notify({
                        type: 'warn',
                        text: '站点分级树获取失败'
                    });
                    return;
                }
                if (!data.data) {
                    error && error();
                    return;
                }

                success({
                    code: setting.rootCode,
                    name: "全部",
                    end: false,
                    nextTier: data.data.firstTier
                });
            }).error(function () {
                console.error("站点分级树获取请求失败！");
            });
        },
        //获取站点树
        getSiteTree: function (success) {
            $http.post(ctx + "/siteFunc/getSitesInTier").success(function (data) {
                if (data.success === false) {
                    $rootScope.notify({
                        type: 'warn',
                        text: '站点树获取失败'
                    });
                    return;
                }
                success({
                    code: setting.rootCode,
                    name: "全部",
                    end: false,
                    nextTier: data.data.firstTier
                });
            }).error(function () {
                console.error("站点树获取请求失败！");
            });
        },
        //获取设备树
        getDcviceTree: function (success) {
            $http.post(ctx + "/siteFunc/getDeviceTierTree").success(function (data) {
                if (data.success === false) {
                    $rootScope.notify({
                        type: 'warn',
                        text: '设备树获取失败'
                    });
                    return;
                }
                success({
                    code: setting.rootCode,
                    name: "站点分级",
                    end: false,
                    nextTier: data.data.firstTier
                });
            }).error(function () {
                console.error("设备树获取请求失败！");
            });
        },
        //获取站点状态树
        getSiteStateTree: function (success) {
            $http.post(ctx + "/siteFunc/getSitesInTier").success(function (data) {
                if (data.success === false) {
                    $rootScope.notify({
                        type: 'warn',
                        text: '站点树获取失败'
                    });
                    return;
                }

                var tree = {
                    code: setting.rootCode,
                    name: "全部",
                    end: false,
                    nextTier: data.data.firstTier
                };
                fsuService.getSiteStateMap(function (map) {
                    insertFsuState(tree, map);
                    success(tree);
                });
                function insertFsuState(node, siteStateMap) {
                    if(!!node.nextTier){
                        for(var i = 0 ; i < node.nextTier.length ; i++){
                            insertFsuState(node.nextTier[i], siteStateMap);
                        }
                    }else{
                        node.fsuState = siteStateMap[node.id];
                    }
                }
            }).error(function () {
                console.error("站点树获取请求失败！");
            });
        },
        //获取权限树
        getPrivilegeTree: function (success) {
            $http.post(ctx + "/userGroupFunc/getPrivilegeTree").success(function (data) {
                if (data.success === false) {
                    $rootScope.notify({
                        type: 'warn',
                        text: '权限树添加失败'
                    });
                    return;
                }
                success({
                    code: setting.rootCode,
                    name: "全部",
                    end: false,
                    children: data.data
                });
            }).error(function () {
                console.error("权限列表请求失败");
            });
        },
        //获取默认层级树
        getDefaultTierTree: function (success) {
            $http.post(ctx + "/systemFunc/getTierTree").success(function (data) {
                if (data.success === false) {
                    $rootScope.notify({
                        type: 'warn',
                        text: '默认站点分级失败'
                    });
                    return;
                }
                success({
                    code: setting.rootCode,
                    name: "全部（您尚未设置站点分级，这是默认分级）",
                    end: false,
                    nextTier: data.data.firstTier
                });
            }).error(function () {
                console.error("默认站点分级请求失败！");
            });
        },
        //将企业的站点分级设置为默认的站点分级
        setDefaultTierTree: function (uniqueCode, success) {
            $http.post(ctx + "/systemFunc/setDefaultTierTree", uniqueCode).success(function (data) {
                if (data.success === false) {
                    $rootScope.notify({
                        type: 'warn',
                        text: '设置默认站点分级失败'
                    });
                    return;
                }
                $rootScope.notify({
                    type: 'success',
                    text: '设置默认站点分级成功'
                });
                success();
            }).error(function () {
                console.error("设置默认站点分级请求失败");
            });
        },
        //初始化树结构，画树
        initTree: function (tree, treeId, treeType, callback) {
            var deepOpen = 2;
            var elemTree = $('#' + treeId);
            switch (treeType) {
                case 'simpleTierTree': //简单层级树
                    openNode(tree, deepOpen);
                    $.fn.zTree.init(elemTree, setting.simple, tree);
                    break;
                case 'radioTierTree': //单选层级树
                    cancelParentNodeRadio(tree);
                    openAllNode(tree);
                    setting.radio.callback.onCheck = callback;
                    $.fn.zTree.init(elemTree, setting.radio, tree);
                    break;
                case 'checkboxTierTree': //复选层级树
                    openAllNode(tree);
                    $.fn.zTree.init(elemTree, setting.checkbox, tree);
                    break;
                case 'checkboxTierTreeOpenOne': //复选层级树,打开一层
                    openNode(tree, 1);
                    $.fn.zTree.init(elemTree, setting.checkbox, tree);
                    break;
                case 'radioSiteTree': //单选站点树
                    cancelParentNodeCheckBySiteLeaf(tree);
                    openAllNode(tree);
                    setting.radio.callback.onCheck = callback;
                    $.fn.zTree.init(elemTree, setting.radio, tree);
                    break;
                case 'radioSiteStateTree': //单选站点树（带在线状态）
                    cancelParentNodeCheckBySiteLeaf(tree);
                    openAllNode(tree);
                    setting.radioState.callback.onCheck = callback;
                    $.fn.zTree.init(elemTree, setting.radioState, tree);
                    break;
                case 'checkboxSiteTree': //复选站点树
                    openAllNode(tree);
                    $.fn.zTree.init(elemTree, setting.checkbox, tree);
                    break;
                case 'checkboxPrivilegeTree': //复选权限树
                    openAllNodeByChildren(tree);
                    $.fn.zTree.init(elemTree, setting.checkboxPrivilege, tree);
                case 'checkboxDeviceTree': //复选设备树
                    openAllNode(tree);
                    setting.checkbox.callback.onCheck = callback;
                    $.fn.zTree.init(elemTree, setting.checkbox, tree);
                    break;
                default:
                    console.log('未定义的树类型');
                    break;
            }
        },
        //创建树的内容
        createZTree: function (data) {
            return {
                zNodes: data,
                isEmpty: false,
                getNodesByParamFuzzy: function (key, treeId, checkType, callback) {
                    if (key === undefined || key === null) {
                        return;
                    }
                    var result = searchInRecursion(this.zNodes, key);
                    if (result.nextTier.length > 0) {
                        this.isEmpty = false;
                        service.initTree(result, treeId, checkType, callback);
                    } else {
                        this.isEmpty = true;
                    }
                }
            };
        },
        //获得复选树中，被选择的节点的code数组
        getCheckedTierCodes: function (treeId) {
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            var nodes = treeObj.getCheckedNodes(true);
            var codes = [];
            for (var i = 0; i <= nodes.length - 1; i++) {
                if (nodes[i].end === true) {
                    codes.push(this.wrapZero(this.getParentCode(nodes[i]), 6));
                }
            }
            return codes;
        },
        //获得复选树中，被选择的节点的权限code数组
        getCheckedPrivilegeCodes: function (treeId) {
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            var nodes = treeObj.getCheckedNodes(true);
            var codes = [];
            for (var i = 0; i <= nodes.length - 1; i++) {
                if (!nodes[i].children) {
                    codes.push(nodes[i].code);
                }
            }
            return codes;
        },
        //获得设备复选树中，被选择的节点的id数组
        getDeviceCheckedTierIds: function (treeId) {
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            var nodes = treeObj.getCheckedNodes(true);
            var ids = [];
            for (var i = 0; i <= nodes.length - 1; i++) {
                if (nodes[i].type === "摄像头") {
                    ids.push(nodes[i].id);
                }
            }
            return ids.length > 0 ? ids : [];
        },
        //获得单选树中，被选择的节点对象
        getCheckedNode: function (treeId) {
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            var nodes = treeObj.getCheckedNodes(true);
            return nodes.length > 0 ? nodes[0] : null;
        },
        //获得复选树中，被选择的节点的数组
        getCheckedNodes: function (treeId) {
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            var nodes = treeObj.getCheckedNodes(true);
            return nodes;
        },
        //选中指定siteId的站点，若siteId为空，则选择第一个站点
        checkNodeBySiteId: function (treeId, siteId) {
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            var node = null;
            if (siteId) {
                node = treeObj.getNodesByFilter(function (node) {
                    return !!node.id && node.id === siteId;
                }, true);
            } else {
                node = treeObj.getNodesByFilter(function (node) {
                    return !!node.id;
                }, true);
            }
            treeObj.checkNode(node, true, true, true);
        },
        //将codes数组对应的节点设置为选中状态
        checkNodeByCodes: function (treeId, codes) {
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            treeObj.checkAllNodes(false);
            if(codes === null || codes.length === 0){
                return;
            }
            var nodes = treeObj.getNodesByFilter(function (node) {
                return codes.containsObj(service.getParentCode(node));
            }, false);
            nodes.forEach(function (node) {
                treeObj.checkNode(node, true, true, true);
            });
        },
        checkedAllNodes: function (treeId, checked) {
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            treeObj.checkAllNodes(checked);
        },
        //将codes数组对应的权限设置为选中状态
        checkPrivilegeNode: function (treeId, codes) {
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            treeObj.checkAllNodes(false);
            var nodes = treeObj.getNodesByFilter(function (node) {
                return codes.containsObj(node.code);
            }, false);
            nodes.forEach(function (node) {
                treeObj.checkNode(node, true, true, true);
            });
        },
        //选中指定设备Id的设备，若设备Id为空，则选择第一个设备
        checkNodeByDeviceId: function (treeId, deviceId, callback) {
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            var node = null;
            if (deviceId) {
                node = treeObj.getNodesByFilter(function (node) {
                    return !!node.id && node.id === deviceId;
                }, true);
            } else {
                node = treeObj.getNodesByFilter(function (node) {
                    return !!node.ip;
                }, true);
            }
            treeObj.checkNode(node, true, true);
            callback && callback();
        },
        //将字符串str前补零至n位
        wrapZero: function (str, n) {
            while (str.length < n) {
                str = '0' + str;
            }
            return str;
        },
        //站点Code分级拼接
        getParentCode: function (obj) {
            if (obj.code !== setting.rootCode) {
                return this.getParentCode(obj.getParentNode()) + obj.code;
            }
            return '';
        },
        //站点名称分级拼接
        getParentName: function (obj, arr) {
            var index = arr.length;
            if (obj.code !== setting.rootCode) {
                arr[index] = {
                    code: obj.code,
                    name: obj.name
                };
                return this.getParentName(obj.getParentNode(), arr) + obj.name + (index === 0 ? '' : '-');
            }
            return '';
        },
        getSetting: function () {
            return setting;
        }
    };
    return service;
}]);