<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\CauHoi;
class CauHoiController extends Controller
{
    public function getDanhSachCauHoi(Request $request){
        $page =$request->query('page',1);
        $limit =$request->query('limit',2);
        //dd($page.'hahahhaha'.$limit);
        $danhSach = CauHoi::orderBy('id','desc')->skip(($page-1)*$limit)->take($limit)->get();
        $result = [
            'success'=>true,
            'count'=>CauHoi::count(),
            'danh_sach'=>$danhSach
        ];
       return response()->json($result);
    }



    public function getAllCauHoi(){
        $cauHoi = CauHoi::all();
        $result = [
            'success' => true,
            'cau_hoi' => $cauHoi
        ];
    
        return response() -> json($result);
    }

    public function getCauHoiByID($id)
    {
        $cauHoi = CauHoi::find($id);
        if($cauHoi == null){
            return response()->json(['success'=>false]);
        }    
        $result = [
            'success' => true,
            'cau_hoi' => $cauHoi
        ];
        return response() -> json($result);
    }
}
