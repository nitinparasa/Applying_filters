/**
 * Created by NitinPC on 18-06-2016.
 */
appFilters.directive('fileModel', ['$parse', function($parse){
  return{
    restrict:'A',
    link:function(scope, element, attrs){
      var model = $parse(attrs.fileModel);
      var modelSetter = model.assign;

      element.bind('change', function(){
        scope.$apply(function(){
          
        })
      })
    }
  }
}])
