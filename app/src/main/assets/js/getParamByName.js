// разбор переданных параметров в строке вызова
// выдача параметра вызова по имени
// Пример:
//   "youfile.html?name1=value1&name2=value2&name3=value3"
//------------------------------------------------------------------------------
function getParamByName(sParamName)
{
var Params = location.search.substring(1).split("&");
// отсекаем "?"  и вносим переменные и их значения в массив var varioable =""
            for (let i = 0; i < Params.length; i++)
                {
                    // если найдена искомая переменная
                    if (Params[i].split("=")[0] == sParamName)
                        {
                                // если значение задано
                             if (Params[i].split("=").length >1 )
                                {
                                    variable = Params[i].split("=")[1] ;
                                    return variable;
                                }
                        }
                }
            return "";
}
