<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mgl" %>
    <!DOCTYPE html>
    <html>

    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
        <script src="resources/static/js/app.js" ></script>
        <script src="resources/static/js/MGL_Task1.service.js"></script>
        <script src="resources/static/js/MGL_Task1.controller.js"></script>
       

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <title>Mist Library Task 1-Games</title>
        <style type="text/css">
            body {
            	background-image:
            		url('https://ak6.picdn.net/shutterstock/videos/1024598666/thumb/1.jpg');
            	background-repeat: no-repeat;
            	background-size: cover;
            }
        </style>
        <link rel="apple-touch-icon" sizes="180x180" href="/android-chrome-192x192.png">
    	<link rel="icon" type="image/png" sizes="32x32" href="resources/static/images/favicon-32x32.png">
    	<link rel="icon" type="image/png" sizes="16x16" href="resources/static/images/favicon-16x16.png">
    </head>

    <body ng-app="MGL_Task1_app" class="ng-cloak">
        <mgl:myNav/>
        <br>
        <div class="container" ng-controller="MGL_Task1_Controller as MGL_T1_ctrl">
            <div class="panel panel-default">
                <div class="panel-heading text-light"><span class="lead">Game Registration Form </span></div>
                <div class="formcontainer" data-ng-if = "!MGL_T1_ctrl.game || !MGL_T1_ctrl.game.id">
                    <form ng-submit="MGL_T1_ctrl.addGame()" name="gameForm" class="form-horizontal">
                        <input type="hidden" ng-model="MGL_T1_ctrl.game.id" />
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-lable text-light" for="game.name">Name*</label>
                                <div class="col-md-7">
                                    <input type="text" ng-model="MGL_T1_ctrl.game.name" id="game.name" class="game.name form-control input-sm" placeholder="Enter the name of the new game [required]" required ng-minlength="3" />
                                    <div class="has-error" ng-show="gameForm.$dirty">
                                        <span ng-show="gameForm.game.name.$error.required">This is a required field</span>
                                        <span ng-show="gameForm.game.name.$error.minlength">Minimum length required is 3</span>
                                        <span ng-show="gameForm.game.name.$invalid">This field is invalid </span>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-lable text-light" for="game.genre">Game Genre</label>
                                <div class="col-md-7">
                                    <input type="text" ng-model="MGL_T1_ctrl.game.genre" id="game.genre" class="form-control input-sm" placeholder="Enter the genre of the new game" />
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-actions floatRight">
                                <input type="submit" value="Add" class="btn btn-primary btn-sm">
                               
                                <button data-ng-if="MGL_T1_ctrl.game" data-ng-click="MGL_T1_ctrl.resetForm()" type="reset" class="btn btn-primary btn-sm">Clear</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            
     
                <div class="formcontainer" data-ng-if = "MGL_T1_ctrl.game.id">
                    <form ng-submit="MGL_T1_ctrl.updateGame()" name="gameForm" class="form-horizontal">
                        <input type="hidden" ng-model="MGL_T1_ctrl.game.id" />
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-lable text-light" for="game.name">Name*</label>
                                <div class="col-md-7">
                                    <input type="text" ng-model="MGL_T1_ctrl.game.name" id="game.name" class="game.name form-control input-sm" placeholder="Enter the name of the new game [required]" required ng-minlength="3" />
                                    <div class="has-error" ng-show="gameForm.$dirty">
                                        <span ng-show="gameForm.game.name.$error.required">This is a required field</span>
                                        <span ng-show="gameForm.game.name.$error.minlength">Minimum length required is 3</span>
                                        <span ng-show="gameForm.game.name.$invalid">This field is invalid </span>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-lable text-light" for="game.genre">Game Genre</label>
                                <div class="col-md-7">
                                    <input type="text" ng-model="MGL_T1_ctrl.game.genre" id="game.genre" class="form-control input-sm" placeholder="Enter the genre of the new game" />
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-actions floatRight">
                                <input type="submit" value="Update" class="btn btn-primary btn-sm">
                                <button data-ng-if="MGL_T1_ctrl.game" data-ng-click="MGL_T1_ctrl.resetForm()" type="reset" class="btn btn-primary btn-sm">Clear</button>
                            </div>
                        </div>
                    </form>
                </div>
      
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading text-light"><span class="lead">List of all current games</span></div>
                <div class="tablecontainer">
                    <table class="table table-dark table-striped text-light">
                        <thead>
                            <tr>
                                <th>Game Name</th>
                                <th>Game Genre</th>
                                <th> Search Games by Name: <form ng-submit="MGL_T1_ctrl.searchByName()" name="nameSearchForm" class="form-horizontal"> 
                                <input type="text" ng-model="MGL_T1_ctrl.nameSearchTerm" class="form-control input-sm" placeholder="Enter the name of the game" />	
                                <input type="submit" value="Search" class="btn btn-primary btn-sm">  <button data-ng-if="MGL_T1_ctrl.nameSearchTerm != ''" data-ng-click="MGL_T1_ctrl.resetSearch()" type="reset" class="btn btn-primary btn-sm">Clear</button>												
                                </form> </th>
                                <th width="20%"></th>
                                <th data-ng-if="MGL_T1_ctrl.sortTypeString != 'genre'"><button data-ng-click = "MGL_T1_ctrl.sortByGenre()" class="btn btn-secondary btn-sm">Sort By Genre</button></th>
                                 <th data-ng-if="MGL_T1_ctrl.sortTypeString != 'default'"><button data-ng-click = "MGL_T1_ctrl.sortDefault();" class="btn btn-secondary btn-sm">Reset Order</button></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="currentGame in MGL_T1_ctrl.games">
                                <td><span ng-bind="currentGame.name"></span></td>
                                <td><span ng-bind="currentGame.genre"></span></td>
                                <td>
                                </td>
                                <td><button data-ng-click = "MGL_T1_ctrl.selectGame(currentGame)" class="btn btn-secondary btn-sm" >Select</button> </td>
                                <td><button data-ng-click="MGL_T1_ctrl.deleteGame(currentGame)" class="btn btn-secondary btn-sm">Delete</button></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </body>
    <script type="text/javascript"></script>

    </html>