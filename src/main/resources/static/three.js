var app = angular.module('myapp', []);
google.load('visualization', '1', {'packages' : ['orgchart']});

app.factory('google', function(){
  return google;
});

app.controller('mycontroller', function($scope, google, $http) {

    $scope.visibilityAddRoot = false;
    $scope.visibilityEdit = false;
    $scope.visibilityChart = true;

    //model data in arrData
    //{v:'id', f:'<div>'+value+'</div>'}, 'parentId', value
    $scope.arrData = [];
    $scope.currentSelected;
    $scope.currentSelectedRow;
    $scope.firstValue = 1;

    $scope.editId = 0;
    $scope.editValue = 0;
    $scope.editParentId = 0;

    //fist load data, work like @PostConstruct.
    $http.get("http://localhost:8080/api/all")
        .then((response) => {
            okResponse(response);
        },(response) => {
            alert("Cannot load data");
        });

    //Function to add root node.
    $scope.addRoot = function () {
        ThreeAddModel.value = $scope.firstValue;

        $http({
            url : 'http://localhost:8080/api/add',
            method :"POST",
            data : JSON.stringify(ThreeAddModel),
            headers : { 'Content-Type' :'application/json; charset=utf-8' }
        }).then((response) => {
                console.log(response);
                findAll();
            }, (response) => {
                console.log(response);
                alert("Error");
            });
    }

    //Function for save new entity in database.
    $scope.save = function () {
        //create model data.
        ThreeAddModel.id = $scope.editId;
        ThreeAddModel.parentId = $scope.editParentId;
        ThreeAddModel.value = $scope.editValue;

        //send
        $http({
            url : 'http://localhost:8080/api/update',
            method :"PUT",
            data : JSON.stringify(ThreeAddModel),
            headers : { 'Content-Type' :'application/json; charset=utf-8' }
        }).then((response) => {
                console.log(response);
                findAll();
            }, (response) => {
                console.log(response);
                alert("Error");
            });
    }

    //Call web service to delete node by entity id.
    $scope.deleted = function () {
        $http({
            url : 'http://localhost:8080/api/delete/'+$scope.editId,
            method :"DELETE",
            headers : { 'Content-Type' :'application/json; charset=utf-8' }
        }).then((response) => {
                console.log(response);
                findAll();
            }, (response) => {
                console.log(response);
                alert("Error");
            });
    }

    //Call web service to save entity.
    $scope.addChildren = function () {
        //create data, we do not have set id.
        ThreeAddModel.id = 0;
        ThreeAddModel.parentId = $scope.editId;
        ThreeAddModel.value = $scope.editValue;

        $http({
            url : 'http://localhost:8080/api/add',
            method :"POST",
            data : JSON.stringify(ThreeAddModel),
            headers : { 'Content-Type' :'application/json; charset=utf-8' }
        }).then((response) => {
                console.log(response);
                findAll();
            }, (response) => {
                console.log(response);
                alert("Error");
            });
    }

    //function reset edit data when user click cancel
    $scope.cancel = function () {
        $scope.editId = 0;
        $scope.editValue = 0;
        $scope.editParentId = 0;
    }

    //function for update all entities
    $scope.updateAll = function () {
        //create new object to hold all entities
        var ManyThreeUpdateModel = new Object();

        //reset data in model
        ManyThreeUpdateModel.entities = [];

        //iterate all collection of entities
        $scope.arrData.forEach(function(arr) {
            //create new entity object
            var ThreeUpdateModel = new Object();

            //set data
            ThreeUpdateModel.id = arr[0].v;
            ThreeUpdateModel.parentId = parseInt(arr[1]);
            ThreeUpdateModel.value = arr[2];

            //save to holder model
            ManyThreeUpdateModel.entities.push(ThreeUpdateModel);
        });

        //send post request
        $http({
            url : 'http://localhost:8080/api/manyupdate',
            method :"PUT",
            data : JSON.stringify(ManyThreeUpdateModel),
            headers : { 'Content-Type' :'application/json; charset=utf-8' }
        }).then((response) => {
                console.log(response);
                findAll();
            }, (response) => {
                console.log(response);
                alert("Error");
            });
    }

    //Get all entity from server.
    function findAll() {
        $http.get("http://localhost:8080/api/all")
            .then((response) => {
                okResponse(response);
            },(response) => {
                alert("Cannot load data");
            });
    }

    //Check from server
    function okResponse(response) {

        if(response.data.length == 0)
            //set visibility of options of add root element.
            $scope.visibilityAddRoot = true;
        else {
            //hide create root options.
            $scope.visibilityAddRoot = false;
            //reset current data.
            $scope.arrData = [];
            //foreach collection and add to data.
            response.data.forEach(function(arr) {
                console.log(arr);
                $scope.arrData.push(createRow(arr));
            });
        }

        drawModel();
    }

    //function create row data to arrData.
    function createRow(arr) {
        return [{v:arr.id.toString(), f:'<div>'+arr.value+'</div>'},
               arr.parentId.toString(),
               arr.value]
    }

    //function draw model and create a callback.
    function drawModel() {
        google.charts.load('current', {packages:["orgchart"]});
        google.charts.setOnLoadCallback(drawChart);
    };

    //function finally draw a chart
    function drawChart() {
        var data = new google.visualization.DataTable();
        var chart = new google.visualization.OrgChart(document.getElementById('orgchart'));

        //create column element
        data.addColumn('string', 'id');
        data.addColumn('string', 'parentId');
        data.addColumn('number', 'value');

        data.addRows($scope.arrData)

        chart.draw(data, {allowHtml:true});

        //add selected listener on chart.
        google.visualization.events.addListener(chart, 'select', function(){
            $scope.currentSelected = $scope.arrData[chart.getSelection()[0].row];
            $scope.currentSelectedRow = chart.getSelection()[0].row;

            //save selected data to edit data
            $scope.editId = $scope.currentSelected[0].v;
            $scope.editValue = $scope.currentSelected[2];
            $scope.editParentId = parseInt($scope.currentSelected[1]);
        });
    };
});