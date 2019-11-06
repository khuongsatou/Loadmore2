@extends('partials.layout')
@include('partials.javascript_data_table')

@section('page_content')
<div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Danh Sách Câu Hỏi</h2>
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
                  <a href="{{ route('cau_hoi.them')}}" style="background-color:#337ab7;color:#fff;border-color:#2e6da4" class="btn btn-app">
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
                          <th>Nội Dung</th>
                          @if(isset($linhVuc))
                          <th>Tên Lĩnh Vực</th>
                          @endif
                          <th>Phương Án A</th>
                          <th>Phương Án B</th>
                          <th>Phương Án C</th>
                          <th>Phương Án D</th>
                          <th>Đáp Án</th>
                          <th></th>
                  
                        </tr>
                      </thead>


                      <tbody>
                        @foreach ($cauHois as $item)
                        <tr>
                          <td>{{ $item->id }}</td>
                          <td>{{ $item->noi_dung }}</td>
                          @if(isset($linhVuc))
                          <td>{{ $linhVuc->ten_linh_vuc }}</td>
                          @endif
                          
                          <td>{{ $item->phuong_an_a }}</td>
                          <td>{{ $item->phuong_an_b }}</td>
                          <td>{{ $item->phuong_an_c }}</td>
                          <td>{{ $item->phuong_an_d }}</td>
                          <td>{{ $item->dap_an }}</td>
                          <td>
                              
                            <a href="{{ route('cau_hoi.cap_nhat',['id' => $item->id]) }}" type="button" class="btn btn-round btn-primary"><i class="fa fa-pencil"></i></a>
                            
                            <a href="{{ route('cau_hoi.xoa',['id' => $item->id]) }}" type="button" class="btn btn-round btn-danger"><i class="fa fa-trash-o"></i></a>
                            </td>
                        </tr>
                        @endforeach
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>

@endsection