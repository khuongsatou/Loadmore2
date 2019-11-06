<?php

use Illuminate\Database\Seeder;
use Illuminate\Support\Str;
use App\LinhVuc;
class ThemLinhVucSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        // DB::table('linh_vuc')->insert([
        // ['ten_linh_vuc' => 'toán'],
        // ['ten_linh_vuc' => 'lý'],
        // ['ten_linh_vuc' => 'hóa'],
        // ['ten_linh_vuc' => 'sinh'],
        // ['ten_linh_vuc' => 'địa']
        // ]);
        $i = 0;
        while($i < 200) {
            LinhVuc::create([
                'ten_linh_vuc'=>"ten".$i
            ]);
            $i++;
        }

        
    }
}
