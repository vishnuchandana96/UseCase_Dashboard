import * as XLSX from "xlsx";

export class Table
 {  
  static exportToExcel(tableId: string, name?: string , displayedColumns? : string) 
  {
   let prefix = "Employees_Dashboard"; 
    let fileName = `${prefix}`;
    let targetTableElm = document.getElementById(tableId);
    let wb = XLSX.utils.table_to_book(targetTableElm, <XLSX.Table2SheetOpts>{ sheet: prefix , width: 50, 
      columnDefs: [
        { field: 'update', exporterSuppressExport: true },
        {field : 'delete' , show : false},
      ], display : true});
    XLSX.writeFile(wb, `${fileName}.xlsx`);
    
  
  }
}
 