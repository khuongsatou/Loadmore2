@extends('partials.layout')
@include('partials.javascript_data_table')

@section('page_content')
<div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Danh Sách Lượt Chơi</h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                      <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                        <ul class="dropdown-menu" role="menu">
                          <!-- setting -->
                        </ul>
                      </li>
                      <li><a class="close-link"><i class="fa fa-close"></i></a>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_support">
                  <a href="{{ route('luot_choi.them')}}" style="background-color:#337ab7;color:#fff;border-color:#2e6da4" class="btn btn-app">
                          <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Thêm Mới
                      </a>
                      <a href="" style="background-color:#4B5F71;color:#fff;border-color:#364B5F" class="btn btn-app">
                          <i class="fa fa-repeat"></i></span> Phục Hồi
                      </a>
                      <a href="" style="background-color:#d9534f;color:#fff;border-color:#d43f3a" class="btn btn-app">
                          <i class="fa fa-edit"></i></span> Lập Báo Cáo
                      </a>
                  </div>
                  <div class="x_content">
                    
                    <table id="datatable" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th>ID</th>
                          @if(isset($nguoiChoi))
                          <th>Tên Đăng Nhập</th>
                          @endif
                          <th>Số Câu</th>
                          <th>Điểm</th>
                          <th>Ngày Giờ</th>
                          <th></th>
                  
                        </tr>
                      </thead>


                      <tbody>
                        @foreach ($luotChois as $item)
                        <tr>
                          <td>{{ $item->id }}</td>
                          @if(isset($nguoiChoi))
                          <td>{{ $nguoiChoi->ten_dang_nhap }}</td>
                          @endif
                          
                          <td>{{ $item->so_cau }}</td>
                          <td>{{ $item->diem }}</td>
                          <td>{{ $item->ngay_gio }}</td>
                          <td>
                            <a href="{{ route('luot_choi.chi_tiet',['id' => $item->id]) }}" type="button" class="btn btn-round btn-warning"><i class="fa fa-list-ul"></i></a>

                            <a href="{{ route('luot_choi.cap_nhat',['id' => $item->id]) }}" type="button" class="btn btn-round btn-primary"><i class="fa fa-pencil"></i></a>
                            
                            <a href="{{ route('luot_choi.xoa',['id' => $item->id]) }}" type="button" class="btn btn-round btn-danger"><i class="fa fa-trash-o"></i></a>
                            </td>
                        </tr>
                        @endforeach
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>

@endsection