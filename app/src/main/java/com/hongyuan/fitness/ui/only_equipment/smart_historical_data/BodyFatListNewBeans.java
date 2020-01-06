package com.hongyuan.fitness.ui.only_equipment.smart_historical_data;

import com.hongyuan.fitness.base.BaseBean;

import java.util.List;

public class BodyFatListNewBeans extends BaseBean {

    /**
     * data : {"list":[{"weight":"76.85","weight_1":"39.76","weight_1_str":"严重偏低","weight_2":"44.73","weight_2_str":"偏低","weight_3":"54.67","weight_3_str":"标准","weight_4":"59.64","weight_4_str":"偏高","weight_5_str":"严重偏高","weight_str":"严重偏高","weight_color":"#E13F14","weight_desc":"体重严重超标，建议低脂、低胆固醇、高纤维膳食，补充 多种维生素，增加运动量进行体重控制。","bmi":"33.70","bmi_1":18.5,"bmi_1_str":"偏低","bmi_2":25,"bmi_2_str":"标准","bmi_3_str":"偏高","bmi_str":"偏高","bmi_color":"#FFAD3E","bmi_desc":"BMI超标，建议选择比较健康的方法减重，如控制饮食、 改变不良生活习惯和参加跑步、跳绳、打篮球、踢足球等 消耗体能的运动。<br>BMI：是指身体质量指数，国际上常用的衡量人体胖瘦程 度以及是否健康的一个标准。","body_fat_per":"33.50","body_fat_per_1":11,"body_fat_per_1_str":"偏低","body_fat_per_2":21,"body_fat_per_2_str":"标准","body_fat_per_3":26,"body_fat_per_3_str":"偏高","body_fat_per_4_str":"严重偏高","body_fat_per_str":"严重偏高","body_fat_per_color":"#E13F14","body_fat_per_desc":"您的体内囤积了太多脂肪，必须检测血压、血糖、肝功能 等情况，是否潜藏危害。赶快开始您的减肥大战，坚持饮 食控制、运动及改变生活方式。","body_water_per":"48.00","body_water_per_1":55,"body_water_per_1_str":"偏低","body_water_per_2":65,"body_water_per_2_str":"标准","body_water_per_3_str":"充足","body_water_per_str":"偏低","body_water_per_color":"#0DC3D1","body_water_per_desc":"体水分率偏低，规律的饮食习惯和每天喝足8杯水可以维 持正常的体水分水平，充足的水分可以促进代谢，带走废 物和身体毒素。","skeletal_muscle_rate":"43.00","skeletal_muscle_rate_1":49,"skeletal_muscle_rate_1_str":"偏低","skeletal_muscle_rate_2":59,"skeletal_muscle_rate_2_str":"标准","skeletal_muscle_rate_3_str":"偏高","skeletal_muscle_rate_str":"偏低","skeletal_muscle_rate_color":"#0DC3D1","skeletal_muscle_rate_desc":"您的骨胳肌比率低于理想范围，跟多静态活动、不运动有 关，会导致基础代谢率降低，腰酸背痛，力量下降，外在 表现是发胖，也容易诱发心血管疾病。<br>骨骼肌率：人体有多个肌肉组成，其中骨胳肌是可以通过 锻炼增加的肌肉。","muscle_mass":"48.55","muscle_mass_1":38.5,"muscle_mass_1_str":"偏低","muscle_mass_2":46.5,"muscle_mass_2_str":"标准","muscle_mass_3_str":"充足","muscle_mass_str":"充足","muscle_mass_color":"#1bc050","muscle_mass_desc":"您的肌肉比较发达，继续保持。","bone_mass":"2.56","bone_mass_1":"3.0","bone_mass_1_str":"偏低","bone_mass_2":"3.4","bone_mass_2_str":"标准","bone_mass_3_str":"偏高","bone_mass_str":"偏低","bone_mass_color":"#0DC3D1","bone_mass_desc":"您的骨量水平偏低。长期低钙饮食、缺乏运动、过度减肥 等都可能引起骨量偏低，多吃含钙高的食物，多晒太阳， 多运动及时补钙。","ffm":"51.11","ffm_str":"标准","ffm_color":"#1bc050","ffm_desc":"去脂体重是指除脂肪以外身体其他成分的重量，肌肉是其 中的主要部分。通过该指标可以看出您锻炼的效果，也可 以看出您减肥的潜力哦！","protein":"15.16","protein_1":"16","protein_1_str":"偏低","protein_2":"18","protein_2_str":"标准","protein_3_str":"充足","protein_str":"偏低","protein_color":"#0DC3D1","protein_desc":"蛋白质不足会引起基础代谢减少，也会引起肌肉的数量减 少。坚持长期运动，适当提高肌肉比例，辅助于蛋白质的 补充，可以提升蛋白质比例。<br>蛋白质:生命的物质基础，是构成细胞的基本有机物。","subcutaneous_fat":"29.00","subcutaneous_fat_1":"8.6","subcutaneous_fat_1_str":"偏低","subcutaneous_fat_2":"16.7","subcutaneous_fat_2_str":"标准","subcutaneous_fat_3_str":"偏高","subcutaneous_fat_str":"偏高","subcutaneous_fat_color":"#FFAD3E","subcutaneous_fat_desc":"皮下脂肪过多是外表肥胖的主要原因，除了有氧减脂以 外，多进行增肌训练，肌肉的增加可以让您拥有更完美的 体型。","visceral_fat_grade":15,"visceral_fat_grade_1":"9","visceral_fat_grade_1_str":"标准","visceral_fat_grade_2":"14","visceral_fat_grade_2_str":"偏高","visceral_fat_grade_3_str":"严重偏高","visceral_fat_grade_str":"严重偏高","visceral_fat_grade_color":"#E13F14","visceral_fat_grade_desc":"内脏脂肪指数危险，罹患心脏病、高血压、高血脂和Ⅱ型 糖尿病风险大，您迫切需要控制体重、积极运动和限制饮 食。","body_age":35,"body_age_str":"不达标","body_age_color":"#FFAD3E","body_age_desc":"您还有年轻空间，加油！","shape":"6.0","shape_str":"肥胖型","shape_color":"#FFAD3E","shape_desc":"肥胖型","basal_metabolism":1473,"basal_metabolism_str":"不达标","basal_metabolism_color":"#FFAD3E","basal_metabolism_desc":"您的标准基础代谢率为1844.4 kcal，目前处于未达标状态。 持续轻量运动能够提高身体的基础代谢率，而节食基础代 谢会大幅下降。","ideal_visual_weight":45.9,"ideal_visual_weight_str":"标准","ideal_visual_weight_color":"#1bc050","ideal_visual_weight_desc":"根据您的身高和年龄，您的理想视觉体重是45.9 kg","fat_control":"-14.22","fat_control_str":"不达标","fat_control_color":"#FFAD3E","fat_control_desc":"根据健康的脂肪控制方式，建议您增加 /减少-14.22 kg脂肪。","inorganic_salt_status_str":"偏低","inorganic_salt_status_color":"#0DC3D1","inorganic_salt_status_desc":"您的骨量水平偏低。长期低钙饮食、缺乏运动、过度减肥 等都可能引起骨量偏低，多吃含钙高的食物，多晒太阳， 多运动及时补钙。","muscle_ratio":"63.18","muscle_ratio_1":"2958.72","muscle_ratio_1_str":"缺乏","muscle_ratio_2":"3573.52","muscle_ratio_2_str":"正常","muscle_ratio_3_str":"充足","muscle_ratio_str":"缺乏","muscle_ratio_color":"#0DC3D1","muscle_ratio_desc":"您的肌肉率为63.18% ，处于缺乏状态，拥有更多的肌肉能 让您展示更好体型。","muscle_control":"4.74","muscle_control_str":"不达标","muscle_control_color":"#FFAD3E","muscle_control_desc":"根据健康的肌肉控制方式，建议您增加 /减少4.74  kg肌肉。","standard_weight":"49.70","standard_weight_str":"标准","standard_weight_color":"#1bc050","standard_weight_desc":"根据您的身高和年龄，您的标准体重是49.70 。","water_content":"36.89","water_content_1":"55","water_content_1_str":"偏低","water_content_2":"65","water_content_2_str":"标准","water_content_3_str":"充足","water_content_str":"偏低","water_content_color":"#0DC3D1","water_content_desc":"体水分率偏低，规律的饮食习惯和每天喝足8杯水可以维 持正常的体水分水平，充足的水分可以促进代谢，带走废 物和身体毒素。","weight_control":"-9.48","weight_control_str":"不达标","weight_control_color":"#FFAD3E","weight_control_desc":"根据健康的体重控制方式，建议您增加 /减少-9.48 kg体重。","obesity_1":"15","obesity_1_str":"标准","obesity_2":"32","obesity_2_str":"超重","obesity_3":"52","obesity_3_str":"轻度肥胖","obesity_4":"74","obesity_4_str":"中度肥胖","obesity_5_str":"严重肥胖","obesity":"54.63","obesity_str":"中度肥胖","obesity_color":"#FFAD3E","obesity_desc":"判断是否肥胖的指标","protein_quality":"11.65","protein_quality_1":"12.30","protein_quality_1_str":"偏低","protein_quality_2":"13.83","protein_quality_2_str":"标准","protein_quality_3_str":"充足","protein_quality_str":"偏低","protein_quality_color":"#0DC3D1","protein_quality_desc":"您缺少足够的蛋白质，需要加强营养，增加蛋白质。","fat_weight":"25.74","fat_weight_1":"8.45","fat_weight_1_str":"偏低","fat_weight_2":"16.14","fat_weight_2_str":"标准","fat_weight_3":"19.98","fat_weight_3_str":"偏高","fat_weight_4_str":"严重偏高","fat_weight_str":"严重偏高","fat_weight_color":"#E13F14","fat_weight_desc":"体内所含脂肪的重量。","bf_record":"58.30","bf_record_str":"58.30","bf_record_color":"#FFAD3E","bf_record_desc":"您的身体状况简直不能忍...接受现实还是改变自己,做出 选择吧！！","birth":"1991-11-11","sex":1,"height":151,"add_date":"2019-11-27 15:50"},{"weight":"76.85","weight_1":"44.24","weight_1_str":"严重偏低","weight_2":"49.77","weight_2_str":"偏低","weight_3":"60.83","weight_3_str":"标准","weight_4":"66.36","weight_4_str":"偏高","weight_5_str":"严重偏高","weight_str":"严重偏高","weight_color":"#E13F14","weight_desc":"体重严重超标，建议低脂、低胆固醇、高纤维膳食，补充 多种维生素，增加运动量进行体重控制。","bmi":"30.40","bmi_1":18.5,"bmi_1_str":"偏低","bmi_2":25,"bmi_2_str":"标准","bmi_3_str":"偏高","bmi_str":"偏高","bmi_color":"#FFAD3E","bmi_desc":"BMI超标，建议选择比较健康的方法减重，如控制饮食、 改变不良生活习惯和参加跑步、跳绳、打篮球、踢足球等 消耗体能的运动。<br>BMI：是指身体质量指数，国际上常用的衡量人体胖瘦程 度以及是否健康的一个标准。","body_fat_per":"30.60","body_fat_per_1":11,"body_fat_per_1_str":"偏低","body_fat_per_2":21,"body_fat_per_2_str":"标准","body_fat_per_3":26,"body_fat_per_3_str":"偏高","body_fat_per_4_str":"严重偏高","body_fat_per_str":"严重偏高","body_fat_per_color":"#E13F14","body_fat_per_desc":"您的体内囤积了太多脂肪，必须检测血压、血糖、肝功能 等情况，是否潜藏危害。赶快开始您的减肥大战，坚持饮 食控制、运动及改变生活方式。","body_water_per":"50.10","body_water_per_1":55,"body_water_per_1_str":"偏低","body_water_per_2":65,"body_water_per_2_str":"标准","body_water_per_3_str":"充足","body_water_per_str":"偏低","body_water_per_color":"#0DC3D1","body_water_per_desc":"体水分率偏低，规律的饮食习惯和每天喝足8杯水可以维 持正常的体水分水平，充足的水分可以促进代谢，带走废 物和身体毒素。","skeletal_muscle_rate":"44.80","skeletal_muscle_rate_1":49,"skeletal_muscle_rate_1_str":"偏低","skeletal_muscle_rate_2":59,"skeletal_muscle_rate_2_str":"标准","skeletal_muscle_rate_3_str":"偏高","skeletal_muscle_rate_str":"偏低","skeletal_muscle_rate_color":"#0DC3D1","skeletal_muscle_rate_desc":"您的骨胳肌比率低于理想范围，跟多静态活动、不运动有 关，会导致基础代谢率降低，腰酸背痛，力量下降，外在 表现是发胖，也容易诱发心血管疾病。<br>骨骼肌率：人体有多个肌肉组成，其中骨胳肌是可以通过 锻炼增加的肌肉。","muscle_mass":"50.67","muscle_mass_1":38.5,"muscle_mass_1_str":"偏低","muscle_mass_2":46.5,"muscle_mass_2_str":"标准","muscle_mass_3_str":"充足","muscle_mass_str":"充足","muscle_mass_color":"#1bc050","muscle_mass_desc":"您的肌肉比较发达，继续保持。","bone_mass":"2.67","bone_mass_1":"3.0","bone_mass_1_str":"偏低","bone_mass_2":"3.4","bone_mass_2_str":"标准","bone_mass_3_str":"偏高","bone_mass_str":"偏低","bone_mass_color":"#0DC3D1","bone_mass_desc":"您的骨量水平偏低。长期低钙饮食、缺乏运动、过度减肥 等都可能引起骨量偏低，多吃含钙高的食物，多晒太阳， 多运动及时补钙。","ffm":"53.34","ffm_str":"标准","ffm_color":"#1bc050","ffm_desc":"去脂体重是指除脂肪以外身体其他成分的重量，肌肉是其 中的主要部分。通过该指标可以看出您锻炼的效果，也可 以看出您减肥的潜力哦！","protein":"15.82","protein_1":"16","protein_1_str":"偏低","protein_2":"18","protein_2_str":"标准","protein_3_str":"充足","protein_str":"偏低","protein_color":"#0DC3D1","protein_desc":"蛋白质不足会引起基础代谢减少，也会引起肌肉的数量减 少。坚持长期运动，适当提高肌肉比例，辅助于蛋白质的 补充，可以提升蛋白质比例。<br>蛋白质:生命的物质基础，是构成细胞的基本有机物。","subcutaneous_fat":"26.90","subcutaneous_fat_1":"8.6","subcutaneous_fat_1_str":"偏低","subcutaneous_fat_2":"16.7","subcutaneous_fat_2_str":"标准","subcutaneous_fat_3_str":"偏高","subcutaneous_fat_str":"偏高","subcutaneous_fat_color":"#FFAD3E","subcutaneous_fat_desc":"皮下脂肪过多是外表肥胖的主要原因，除了有氧减脂以 外，多进行增肌训练，肌肉的增加可以让您拥有更完美的 体型。","visceral_fat_grade":12,"visceral_fat_grade_1":"9","visceral_fat_grade_1_str":"标准","visceral_fat_grade_2":"14","visceral_fat_grade_2_str":"偏高","visceral_fat_grade_3_str":"严重偏高","visceral_fat_grade_str":"偏高","visceral_fat_grade_color":"#FFAD3E","visceral_fat_grade_desc":"内脏脂肪指数偏高，持续保持均衡的饮食和适当的运动， 以标准程度为目标，进行适当运动和限制卡路里。","body_age":34,"body_age_str":"不达标","body_age_color":"#FFAD3E","body_age_desc":"您还有年轻空间，加油！","shape":"6.0","shape_str":"肥胖型","shape_color":"#FFAD3E","shape_desc":"肥胖型","basal_metabolism":1522,"basal_metabolism_str":"不达标","basal_metabolism_color":"#FFAD3E","basal_metabolism_desc":"您的标准基础代谢率为1844.4 kcal，目前处于未达标状态。 持续轻量运动能够提高身体的基础代谢率，而节食基础代 谢会大幅下降。","ideal_visual_weight":53.1,"ideal_visual_weight_str":"标准","ideal_visual_weight_color":"#1bc050","ideal_visual_weight_desc":"根据您的身高和年龄，您的理想视觉体重是53.1 kg","fat_control":"-11.99","fat_control_str":"不达标","fat_control_color":"#FFAD3E","fat_control_desc":"根据健康的脂肪控制方式，建议您增加 /减少-11.99 kg脂肪。","inorganic_salt_status_str":"偏低","inorganic_salt_status_color":"#0DC3D1","inorganic_salt_status_desc":"您的骨量水平偏低。长期低钙饮食、缺乏运动、过度减肥 等都可能引起骨量偏低，多吃含钙高的食物，多晒太阳， 多运动及时补钙。","muscle_ratio":"65.93","muscle_ratio_1":"2958.72","muscle_ratio_1_str":"缺乏","muscle_ratio_2":"3573.52","muscle_ratio_2_str":"正常","muscle_ratio_3_str":"充足","muscle_ratio_str":"缺乏","muscle_ratio_color":"#0DC3D1","muscle_ratio_desc":"您的肌肉率为65.93% ，处于缺乏状态，拥有更多的肌肉能 让您展示更好体型。","muscle_control":"4.00","muscle_control_str":"不达标","muscle_control_color":"#FFAD3E","muscle_control_desc":"根据健康的肌肉控制方式，建议您增加 /减少4.00  kg肌肉。","standard_weight":"55.30","standard_weight_str":"标准","standard_weight_color":"#1bc050","standard_weight_desc":"根据您的身高和年龄，您的标准体重是55.30 。","water_content":"38.50","water_content_1":"55","water_content_1_str":"偏低","water_content_2":"65","water_content_2_str":"标准","water_content_3_str":"充足","water_content_str":"偏低","water_content_color":"#0DC3D1","water_content_desc":"体水分率偏低，规律的饮食习惯和每天喝足8杯水可以维 持正常的体水分水平，充足的水分可以促进代谢，带走废 物和身体毒素。","weight_control":"-7.99","weight_control_str":"不达标","weight_control_color":"#FFAD3E","weight_control_desc":"根据健康的体重控制方式，建议您增加 /减少-7.99 kg体重。","obesity_1":"15","obesity_1_str":"标准","obesity_2":"32","obesity_2_str":"超重","obesity_3":"52","obesity_3_str":"轻度肥胖","obesity_4":"74","obesity_4_str":"中度肥胖","obesity_5_str":"严重肥胖","obesity":"38.97","obesity_str":"轻度肥胖","obesity_color":"#FFAD3E","obesity_desc":"判断是否肥胖的指标","protein_quality":"12.16","protein_quality_1":"12.30","protein_quality_1_str":"偏低","protein_quality_2":"13.83","protein_quality_2_str":"标准","protein_quality_3_str":"充足","protein_quality_str":"偏低","protein_quality_color":"#0DC3D1","protein_quality_desc":"您缺少足够的蛋白质，需要加强营养，增加蛋白质。","fat_weight":"23.52","fat_weight_1":"8.45","fat_weight_1_str":"偏低","fat_weight_2":"16.14","fat_weight_2_str":"标准","fat_weight_3":"19.98","fat_weight_3_str":"偏高","fat_weight_4_str":"严重偏高","fat_weight_str":"严重偏高","fat_weight_color":"#E13F14","fat_weight_desc":"体内所含脂肪的重量。","bf_record":"64.20","bf_record_str":"64.20","bf_record_color":"#FFAD3E","bf_record_desc":"良好的体型是健康表现之一，您可能要变形了！","birth":"1991-11-11","sex":1,"height":159,"add_date":"2019-11-27 15:50"},{"weight":"76.85","weight_1":"50.96","weight_1_str":"严重偏低","weight_2":"57.33","weight_2_str":"偏低","weight_3":"70.07","weight_3_str":"标准","weight_4":"76.44","weight_4_str":"偏高","weight_5_str":"严重偏高","weight_str":"严重偏高","weight_color":"#E13F14","weight_desc":"体重严重超标，建议低脂、低胆固醇、高纤维膳食，补充 多种维生素，增加运动量进行体重控制。","bmi":"26.30","bmi_1":18.5,"bmi_1_str":"偏低","bmi_2":25,"bmi_2_str":"标准","bmi_3_str":"偏高","bmi_str":"偏高","bmi_color":"#FFAD3E","bmi_desc":"BMI超标，建议选择比较健康的方法减重，如控制饮食、 改变不良生活习惯和参加跑步、跳绳、打篮球、踢足球等 消耗体能的运动。<br>BMI：是指身体质量指数，国际上常用的衡量人体胖瘦程 度以及是否健康的一个标准。","body_fat_per":"25.90","body_fat_per_1":11,"body_fat_per_1_str":"偏低","body_fat_per_2":21,"body_fat_per_2_str":"标准","body_fat_per_3":26,"body_fat_per_3_str":"偏高","body_fat_per_4_str":"严重偏高","body_fat_per_str":"偏高","body_fat_per_color":"#FFAD3E","body_fat_per_desc":"要匀称不显胖，每日有氧运动要持续30分钟，体脂率才 会开始燃烧，快走、慢跑、游泳、爬楼梯、骑自行车都是 很好的选择。","body_water_per":"53.50","body_water_per_1":55,"body_water_per_1_str":"偏低","body_water_per_2":65,"body_water_per_2_str":"标准","body_water_per_3_str":"充足","body_water_per_str":"偏低","body_water_per_color":"#0DC3D1","body_water_per_desc":"体水分率偏低，规律的饮食习惯和每天喝足8杯水可以维 持正常的体水分水平，充足的水分可以促进代谢，带走废 物和身体毒素。","skeletal_muscle_rate":"47.80","skeletal_muscle_rate_1":49,"skeletal_muscle_rate_1_str":"偏低","skeletal_muscle_rate_2":59,"skeletal_muscle_rate_2_str":"标准","skeletal_muscle_rate_3_str":"偏高","skeletal_muscle_rate_str":"偏低","skeletal_muscle_rate_color":"#0DC3D1","skeletal_muscle_rate_desc":"您的骨胳肌比率低于理想范围，跟多静态活动、不运动有 关，会导致基础代谢率降低，腰酸背痛，力量下降，外在 表现是发胖，也容易诱发心血管疾病。<br>骨骼肌率：人体有多个肌肉组成，其中骨胳肌是可以通过 锻炼增加的肌肉。","muscle_mass":"54.10","muscle_mass_1":"49.4","muscle_mass_1_str":"偏低","muscle_mass_2":"59.4","muscle_mass_2_str":"标准","muscle_mass_3_str":"充足","muscle_mass_str":"标准","muscle_mass_color":"#1bc050","muscle_mass_desc":"您的肌肉比较发达，继续保持。","bone_mass":"2.85","bone_mass_1":"3.0","bone_mass_1_str":"偏低","bone_mass_2":"3.4","bone_mass_2_str":"标准","bone_mass_3_str":"偏高","bone_mass_str":"偏低","bone_mass_color":"#0DC3D1","bone_mass_desc":"您的骨量水平偏低。长期低钙饮食、缺乏运动、过度减肥 等都可能引起骨量偏低，多吃含钙高的食物，多晒太阳， 多运动及时补钙。","ffm":"56.91","ffm_str":"标准","ffm_color":"#1bc050","ffm_desc":"去脂体重是指除脂肪以外身体其他成分的重量，肌肉是其 中的主要部分。通过该指标可以看出您锻炼的效果，也可 以看出您减肥的潜力哦！","protein":"16.93","protein_1":"16","protein_1_str":"偏低","protein_2":"18","protein_2_str":"标准","protein_3_str":"充足","protein_str":"标准","protein_color":"#1bc050","protein_desc":"您的蛋白质处于标准水平。<br>蛋白质:生命的物质基础，是构成细胞的基本有机物。","subcutaneous_fat":"23.00","subcutaneous_fat_1":"8.6","subcutaneous_fat_1_str":"偏低","subcutaneous_fat_2":"16.7","subcutaneous_fat_2_str":"标准","subcutaneous_fat_3_str":"偏高","subcutaneous_fat_str":"偏高","subcutaneous_fat_color":"#FFAD3E","subcutaneous_fat_desc":"皮下脂肪过多是外表肥胖的主要原因，除了有氧减脂以 外，多进行增肌训练，肌肉的增加可以让您拥有更完美的 体型。","visceral_fat_grade":9,"visceral_fat_grade_1":"9","visceral_fat_grade_1_str":"标准","visceral_fat_grade_2":"14","visceral_fat_grade_2_str":"偏高","visceral_fat_grade_3_str":"严重偏高","visceral_fat_grade_str":"标准","visceral_fat_grade_color":"#1bc050","visceral_fat_grade_desc":"内脏脂肪指数标准，暂时没有太大风险。","body_age":29,"body_age_str":"不达标","body_age_color":"#FFAD3E","body_age_desc":"您还有年轻空间，加油！","shape":"7.0","shape_str":"偏胖型","shape_color":"#FFAD3E","shape_desc":"偏胖型","basal_metabolism":1600,"basal_metabolism_str":"不达标","basal_metabolism_color":"#FFAD3E","basal_metabolism_desc":"您的标准基础代谢率为1844.4 kcal，目前处于未达标状态。 持续轻量运动能够提高身体的基础代谢率，而节食基础代 谢会大幅下降。","ideal_visual_weight":63.9,"ideal_visual_weight_str":"标准","ideal_visual_weight_color":"#1bc050","ideal_visual_weight_desc":"根据您的身高和年龄，您的理想视觉体重是63.9 kg","fat_control":"-8.38","fat_control_str":"不达标","fat_control_color":"#FFAD3E","fat_control_desc":"根据健康的脂肪控制方式，建议您增加 /减少-8.38 kg脂肪。","inorganic_salt_status_str":"偏低","inorganic_salt_status_color":"#0DC3D1","inorganic_salt_status_desc":"您的骨量水平偏低。长期低钙饮食、缺乏运动、过度减肥 等都可能引起骨量偏低，多吃含钙高的食物，多晒太阳， 多运动及时补钙。","muscle_ratio":"70.40","muscle_ratio_1":"3796.39","muscle_ratio_1_str":"缺乏","muscle_ratio_2":"4564.89","muscle_ratio_2_str":"正常","muscle_ratio_3_str":"充足","muscle_ratio_str":"缺乏","muscle_ratio_color":"#0DC3D1","muscle_ratio_desc":"您的肌肉率为70.40% ，处于缺乏状态，拥有更多的肌肉能 让您展示更好体型。","muscle_control":"2.79","muscle_control_str":"不达标","muscle_control_color":"#FFAD3E","muscle_control_desc":"根据健康的肌肉控制方式，建议您增加 /减少2.79  kg肌肉。","standard_weight":"63.70","standard_weight_str":"标准","standard_weight_color":"#1bc050","standard_weight_desc":"根据您的身高和年龄，您的标准体重是63.70 。","water_content":"41.11","water_content_1":"55","water_content_1_str":"偏低","water_content_2":"65","water_content_2_str":"标准","water_content_3_str":"充足","water_content_str":"偏低","water_content_color":"#0DC3D1","water_content_desc":"体水分率偏低，规律的饮食习惯和每天喝足8杯水可以维 持正常的体水分水平，充足的水分可以促进代谢，带走废 物和身体毒素。","weight_control":"-5.58","weight_control_str":"不达标","weight_control_color":"#FFAD3E","weight_control_desc":"根据健康的体重控制方式，建议您增加 /减少-5.58 kg体重。","obesity_1":"15","obesity_1_str":"标准","obesity_2":"32","obesity_2_str":"超重","obesity_3":"52","obesity_3_str":"轻度肥胖","obesity_4":"74","obesity_4_str":"中度肥胖","obesity_5_str":"严重肥胖","obesity":"20.64","obesity_str":"超重","obesity_color":"#FFAD3E","obesity_desc":"判断是否肥胖的指标","protein_quality":"13.01","protein_quality_1":"12.30","protein_quality_1_str":"偏低","protein_quality_2":"13.83","protein_quality_2_str":"标准","protein_quality_3_str":"充足","protein_quality_str":"标准","protein_quality_color":"#1bc050","protein_quality_desc":"您的蛋白质正常，继续保持。","fat_weight":"19.90","fat_weight_1":"8.45","fat_weight_1_str":"偏低","fat_weight_2":"16.14","fat_weight_2_str":"标准","fat_weight_3":"19.98","fat_weight_3_str":"偏高","fat_weight_4_str":"严重偏高","fat_weight_str":"偏高","fat_weight_color":"#FFAD3E","fat_weight_desc":"体内所含脂肪的重量。","bf_record":"77.40","bf_record_str":"77.40","bf_record_color":"#FFAD3E","bf_record_desc":"您正在远离男神的体质了，运动起来吧！","birth":"1991-11-11","sex":1,"height":171,"add_date":"2019-11-27 15:50"},{"weight":"76.85","weight_1":"54.32","weight_1_str":"严重偏低","weight_2":"61.11","weight_2_str":"偏低","weight_3":"74.69","weight_3_str":"标准","weight_4":"81.48","weight_4_str":"偏高","weight_5_str":"严重偏高","weight_str":"偏高","weight_color":"#FFAD3E","weight_desc":"体重偏重，略显肥胖，建议一周进行３-５次有氧运动， 减少主食（米饭面食等）的摄入，增加高纤维粗粮比例。","bmi":"24.50","bmi_1":18.5,"bmi_1_str":"偏低","bmi_2":25,"bmi_2_str":"标准","bmi_3_str":"偏高","bmi_str":"标准","bmi_color":"#1bc050","bmi_desc":"BMI：是指身体质量指数，国际上常用的衡量人体胖瘦程 度以及是否健康的一个标准。","body_fat_per":"23.50","body_fat_per_1":11,"body_fat_per_1_str":"偏低","body_fat_per_2":21,"body_fat_per_2_str":"标准","body_fat_per_3":26,"body_fat_per_3_str":"偏高","body_fat_per_4_str":"严重偏高","body_fat_per_str":"偏高","body_fat_per_color":"#FFAD3E","body_fat_per_desc":"要匀称不显胖，每日有氧运动要持续30分钟，体脂率才 会开始燃烧，快走、慢跑、游泳、爬楼梯、骑自行车都是 很好的选择。","body_water_per":"55.20","body_water_per_1":55,"body_water_per_1_str":"偏低","body_water_per_2":65,"body_water_per_2_str":"标准","body_water_per_3_str":"充足","body_water_per_str":"标准","body_water_per_color":"#1bc050","body_water_per_desc":"身体水分率处于标准值，适量饮水，适当运动，均衡饮 食，保持身体水分的平衡。","skeletal_muscle_rate":"49.40","skeletal_muscle_rate_1":49,"skeletal_muscle_rate_1_str":"偏低","skeletal_muscle_rate_2":59,"skeletal_muscle_rate_2_str":"标准","skeletal_muscle_rate_3_str":"偏高","skeletal_muscle_rate_str":"标准","skeletal_muscle_rate_color":"#1bc050","skeletal_muscle_rate_desc":"您的骨胳肌比率处于标准范围。运动量过少或者节食会导 致肌肉流失，请保持适当的运动量和合理的饮食。<br>骨骼肌率：人体有多个肌肉组成，其中骨胳肌是可以通过 锻炼增加的肌肉。","muscle_mass":"55.85","muscle_mass_1":"49.4","muscle_mass_1_str":"偏低","muscle_mass_2":"59.4","muscle_mass_2_str":"标准","muscle_mass_3_str":"充足","muscle_mass_str":"标准","muscle_mass_color":"#1bc050","muscle_mass_desc":"您的肌肉比较发达，继续保持。","bone_mass":"2.94","bone_mass_1":"3.0","bone_mass_1_str":"偏低","bone_mass_2":"3.4","bone_mass_2_str":"标准","bone_mass_3_str":"偏高","bone_mass_str":"偏低","bone_mass_color":"#0DC3D1","bone_mass_desc":"您的骨量水平偏低。长期低钙饮食、缺乏运动、过度减肥 等都可能引起骨量偏低，多吃含钙高的食物，多晒太阳， 多运动及时补钙。","ffm":"58.79","ffm_str":"标准","ffm_color":"#1bc050","ffm_desc":"去脂体重是指除脂肪以外身体其他成分的重量，肌肉是其 中的主要部分。通过该指标可以看出您锻炼的效果，也可 以看出您减肥的潜力哦！","protein":"17.44","protein_1":"16","protein_1_str":"偏低","protein_2":"18","protein_2_str":"标准","protein_3_str":"充足","protein_str":"标准","protein_color":"#1bc050","protein_desc":"您的蛋白质处于标准水平。<br>蛋白质:生命的物质基础，是构成细胞的基本有机物。","subcutaneous_fat":"21.10","subcutaneous_fat_1":"8.6","subcutaneous_fat_1_str":"偏低","subcutaneous_fat_2":"16.7","subcutaneous_fat_2_str":"标准","subcutaneous_fat_3_str":"偏高","subcutaneous_fat_str":"偏高","subcutaneous_fat_color":"#FFAD3E","subcutaneous_fat_desc":"皮下脂肪过多是外表肥胖的主要原因，除了有氧减脂以 外，多进行增肌训练，肌肉的增加可以让您拥有更完美的 体型。","visceral_fat_grade":7,"visceral_fat_grade_1":"9","visceral_fat_grade_1_str":"标准","visceral_fat_grade_2":"14","visceral_fat_grade_2_str":"偏高","visceral_fat_grade_3_str":"严重偏高","visceral_fat_grade_str":"标准","visceral_fat_grade_color":"#1bc050","visceral_fat_grade_desc":"内脏脂肪指数标准，暂时没有太大风险。","body_age":27,"body_age_str":"达标","body_age_color":"#1bc050","body_age_desc":"您还有年轻空间，加油！","shape":"1.0","shape_str":"隐性肥胖型","shape_color":"#FFAD3E","shape_desc":"隐性肥胖型","basal_metabolism":1639,"basal_metabolism_str":"不达标","basal_metabolism_color":"#FFAD3E","basal_metabolism_desc":"您的标准基础代谢率为1844.4 kcal，目前处于未达标状态。 持续轻量运动能够提高身体的基础代谢率，而节食基础代 谢会大幅下降。","ideal_visual_weight":69.3,"ideal_visual_weight_str":"标准","ideal_visual_weight_color":"#1bc050","ideal_visual_weight_desc":"根据您的身高和年龄，您的理想视觉体重是69.3 kg","fat_control":"-6.53","fat_control_str":"不达标","fat_control_color":"#FFAD3E","fat_control_desc":"根据健康的脂肪控制方式，建议您增加 /减少-6.53 kg脂肪。","inorganic_salt_status_str":"偏低","inorganic_salt_status_color":"#0DC3D1","inorganic_salt_status_desc":"您的骨量水平偏低。长期低钙饮食、缺乏运动、过度减肥 等都可能引起骨量偏低，多吃含钙高的食物，多晒太阳， 多运动及时补钙。","muscle_ratio":"72.67","muscle_ratio_1":"3796.39","muscle_ratio_1_str":"缺乏","muscle_ratio_2":"4564.89","muscle_ratio_2_str":"正常","muscle_ratio_3_str":"充足","muscle_ratio_str":"缺乏","muscle_ratio_color":"#0DC3D1","muscle_ratio_desc":"您的肌肉率为72.67% ，处于缺乏状态，拥有更多的肌肉能 让您展示更好体型。","muscle_control":"2.18","muscle_control_str":"不达标","muscle_control_color":"#FFAD3E","muscle_control_desc":"根据健康的肌肉控制方式，建议您增加 /减少2.18  kg肌肉。","standard_weight":"67.90","standard_weight_str":"标准","standard_weight_color":"#1bc050","standard_weight_desc":"根据您的身高和年龄，您的标准体重是67.90 。","water_content":"42.42","water_content_1":"55","water_content_1_str":"偏低","water_content_2":"65","water_content_2_str":"标准","water_content_3_str":"充足","water_content_str":"偏低","water_content_color":"#0DC3D1","water_content_desc":"体水分率偏低，规律的饮食习惯和每天喝足8杯水可以维 持正常的体水分水平，充足的水分可以促进代谢，带走废 物和身体毒素。","weight_control":"-4.35","weight_control_str":"不达标","weight_control_color":"#FFAD3E","weight_control_desc":"根据健康的体重控制方式，建议您增加 /减少-4.35 kg体重。","obesity_1":"15","obesity_1_str":"标准","obesity_2":"32","obesity_2_str":"超重","obesity_3":"52","obesity_3_str":"轻度肥胖","obesity_4":"74","obesity_4_str":"中度肥胖","obesity_5_str":"严重肥胖","obesity":"13.18","obesity_str":"标准","obesity_color":"#1bc050","obesity_desc":"判断是否肥胖的指标","protein_quality":"13.40","protein_quality_1":"12.30","protein_quality_1_str":"偏低","protein_quality_2":"13.83","protein_quality_2_str":"标准","protein_quality_3_str":"充足","protein_quality_str":"标准","protein_quality_color":"#1bc050","protein_quality_desc":"您的蛋白质正常，继续保持。","fat_weight":"18.06","fat_weight_1":"8.45","fat_weight_1_str":"偏低","fat_weight_2":"16.14","fat_weight_2_str":"标准","fat_weight_3":"19.98","fat_weight_3_str":"偏高","fat_weight_4_str":"严重偏高","fat_weight_str":"偏高","fat_weight_color":"#FFAD3E","fat_weight_desc":"体内所含脂肪的重量。","bf_record":"85.10","bf_record_str":"85.10","bf_record_color":"#1bc050","bf_record_desc":"您的体质已经接近男神了，继续努力就能梦想成真！！","birth":"1991-11-11","sex":1,"height":177,"add_date":"2019-11-27 15:50"},{"weight":"76.85","weight_1":"60.48","weight_1_str":"严重偏低","weight_2":"68.04","weight_2_str":"偏低","weight_3":"83.16","weight_3_str":"标准","weight_4":"90.72","weight_4_str":"偏高","weight_5_str":"严重偏高","weight_str":"标准","weight_color":"#1bc050","weight_desc":"恭喜您拥有理想的体重，保持合理健康的生活方式，适量 参加运动，您就可以维持标准体重了。","bmi":"21.70","bmi_1":18.5,"bmi_1_str":"偏低","bmi_2":25,"bmi_2_str":"标准","bmi_3_str":"偏高","bmi_str":"标准","bmi_color":"#1bc050","bmi_desc":"BMI：是指身体质量指数，国际上常用的衡量人体胖瘦程 度以及是否健康的一个标准。","body_fat_per":"18.80","body_fat_per_1":11,"body_fat_per_1_str":"偏低","body_fat_per_2":21,"body_fat_per_2_str":"标准","body_fat_per_3":26,"body_fat_per_3_str":"偏高","body_fat_per_4_str":"严重偏高","body_fat_per_str":"标准","body_fat_per_color":"#1bc050","body_fat_per_desc":"目前您的体脂率处于标准范围，保持好的饮食方式和生活 习惯是保持健康身材的最佳途径。","body_water_per":"58.60","body_water_per_1":55,"body_water_per_1_str":"偏低","body_water_per_2":65,"body_water_per_2_str":"标准","body_water_per_3_str":"充足","body_water_per_str":"标准","body_water_per_color":"#1bc050","body_water_per_desc":"身体水分率处于标准值，适量饮水，适当运动，均衡饮 食，保持身体水分的平衡。","skeletal_muscle_rate":"52.50","skeletal_muscle_rate_1":49,"skeletal_muscle_rate_1_str":"偏低","skeletal_muscle_rate_2":59,"skeletal_muscle_rate_2_str":"标准","skeletal_muscle_rate_3_str":"偏高","skeletal_muscle_rate_str":"标准","skeletal_muscle_rate_color":"#1bc050","skeletal_muscle_rate_desc":"您的骨胳肌比率处于标准范围。运动量过少或者节食会导 致肌肉流失，请保持适当的运动量和合理的饮食。<br>骨骼肌率：人体有多个肌肉组成，其中骨胳肌是可以通过 锻炼增加的肌肉。","muscle_mass":"59.28","muscle_mass_1":"49.4","muscle_mass_1_str":"偏低","muscle_mass_2":"59.4","muscle_mass_2_str":"标准","muscle_mass_3_str":"充足","muscle_mass_str":"标准","muscle_mass_color":"#1bc050","muscle_mass_desc":"您的肌肉比较发达，继续保持。","bone_mass":"3.12","bone_mass_1":"3.0","bone_mass_1_str":"偏低","bone_mass_2":"3.4","bone_mass_2_str":"标准","bone_mass_3_str":"偏高","bone_mass_str":"标准","bone_mass_color":"#1bc050","bone_mass_desc":"您的骨量水平标准。骨量在短期内不会出现明显的变化， 您只要保证健康的饮食和适当的锻炼，就可以维持稳定健 康的骨量水平。","ffm":"62.40","ffm_str":"标准","ffm_color":"#1bc050","ffm_desc":"去脂体重是指除脂肪以外身体其他成分的重量，肌肉是其 中的主要部分。通过该指标可以看出您锻炼的效果，也可 以看出您减肥的潜力哦！","protein":"18.52","protein_1":"16","protein_1_str":"偏低","protein_2":"18","protein_2_str":"标准","protein_3_str":"充足","protein_str":"充足","protein_color":"#1bc050","protein_desc":"蛋白质比例充足。<br>蛋白质:生命的物质基础，是构成细胞的基本有机物。","subcutaneous_fat":"17.00","subcutaneous_fat_1":"8.6","subcutaneous_fat_1_str":"偏低","subcutaneous_fat_2":"16.7","subcutaneous_fat_2_str":"标准","subcutaneous_fat_3_str":"偏高","subcutaneous_fat_str":"偏高","subcutaneous_fat_color":"#FFAD3E","subcutaneous_fat_desc":"皮下脂肪过多是外表肥胖的主要原因，除了有氧减脂以 外，多进行增肌训练，肌肉的增加可以让您拥有更完美的 体型。","visceral_fat_grade":5,"visceral_fat_grade_1":"9","visceral_fat_grade_1_str":"标准","visceral_fat_grade_2":"14","visceral_fat_grade_2_str":"偏高","visceral_fat_grade_3_str":"严重偏高","visceral_fat_grade_str":"标准","visceral_fat_grade_color":"#1bc050","visceral_fat_grade_desc":"内脏脂肪指数标准，暂时没有太大风险。","body_age":22,"body_age_str":"达标","body_age_color":"#1bc050","body_age_desc":"您还有年轻空间，加油！","shape":"4.0","shape_str":"标准型","shape_color":"#1bc050","shape_desc":"标准型","basal_metabolism":1717,"basal_metabolism_str":"不达标","basal_metabolism_color":"#FFAD3E","basal_metabolism_desc":"您的标准基础代谢率为1844.4 kcal，目前处于未达标状态。 持续轻量运动能够提高身体的基础代谢率，而节食基础代 谢会大幅下降。","ideal_visual_weight":79.2,"ideal_visual_weight_str":"标准","ideal_visual_weight_color":"#1bc050","ideal_visual_weight_desc":"根据您的身高和年龄，您的理想视觉体重是79.2 kg","fat_control":"-2.92","fat_control_str":"不达标","fat_control_color":"#FFAD3E","fat_control_desc":"根据健康的脂肪控制方式，建议您增加 /减少-2.92 kg脂肪。","inorganic_salt_status_str":"标准","inorganic_salt_status_color":"#1bc050","inorganic_salt_status_desc":"您的骨量水平标准。骨量在短期内不会出现明显的变化， 您只要保证健康的饮食和适当的锻炼，就可以维持稳定健 康的骨量水平。","muscle_ratio":"77.14","muscle_ratio_1":"3796.39","muscle_ratio_1_str":"缺乏","muscle_ratio_2":"4564.89","muscle_ratio_2_str":"正常","muscle_ratio_3_str":"充足","muscle_ratio_str":"缺乏","muscle_ratio_color":"#0DC3D1","muscle_ratio_desc":"您的肌肉率为77.14% ，处于缺乏状态，拥有更多的肌肉能 让您展示更好体型。","muscle_control":"0.97","muscle_control_str":"不达标","muscle_control_color":"#FFAD3E","muscle_control_desc":"根据健康的肌肉控制方式，建议您增加 /减少0.97  kg肌肉。","standard_weight":"75.60","standard_weight_str":"标准","standard_weight_color":"#1bc050","standard_weight_desc":"根据您的身高和年龄，您的标准体重是75.60 。","water_content":"45.03","water_content_1":"55","water_content_1_str":"偏低","water_content_2":"65","water_content_2_str":"标准","water_content_3_str":"充足","water_content_str":"偏低","water_content_color":"#0DC3D1","water_content_desc":"体水分率偏低，规律的饮食习惯和每天喝足8杯水可以维 持正常的体水分水平，充足的水分可以促进代谢，带走废 物和身体毒素。","weight_control":"-1.95","weight_control_str":"不达标","weight_control_color":"#FFAD3E","weight_control_desc":"根据健康的体重控制方式，建议您增加 /减少-1.95 kg体重。","obesity_1":"15","obesity_1_str":"标准","obesity_2":"32","obesity_2_str":"超重","obesity_3":"52","obesity_3_str":"轻度肥胖","obesity_4":"74","obesity_4_str":"中度肥胖","obesity_5_str":"严重肥胖","obesity":"1.65","obesity_str":"标准","obesity_color":"#1bc050","obesity_desc":"判断是否肥胖的指标","protein_quality":"14.23","protein_quality_1":"12.30","protein_quality_1_str":"偏低","protein_quality_2":"13.83","protein_quality_2_str":"标准","protein_quality_3_str":"充足","protein_quality_str":"充足","protein_quality_color":"#1bc050","protein_quality_desc":"您的蛋白质充足，继续保持。","fat_weight":"14.45","fat_weight_1":"8.45","fat_weight_1_str":"偏低","fat_weight_2":"16.14","fat_weight_2_str":"标准","fat_weight_3":"19.98","fat_weight_3_str":"偏高","fat_weight_4_str":"严重偏高","fat_weight_str":"标准","fat_weight_color":"#1bc050","fat_weight_desc":"体内所含脂肪的重量。","bf_record":"97.10","bf_record_str":"97.10","bf_record_color":"#1bc050","bf_record_desc":"您的身体状况已经超神，妥善保养就行了！！","birth":"1991-11-11","sex":1,"height":188,"add_date":"2019-11-27 15:50"},{"weight":"76.85","weight_1":"70.56","weight_1_str":"严重偏低","weight_2":"79.38","weight_2_str":"偏低","weight_3":"97.02","weight_3_str":"标准","weight_4":"105.84","weight_4_str":"偏高","weight_5_str":"严重偏高","weight_str":"偏低","weight_color":"#0DC3D1","weight_desc":"体重偏低，身体消瘦，建议加强营养，平衡饮食，多吃高 蛋白食物，摄入更多的热量以增加体重。","bmi":"18.10","bmi_1":18.5,"bmi_1_str":"偏低","bmi_2":25,"bmi_2_str":"标准","bmi_3_str":"偏高","bmi_str":"偏低","bmi_color":"#0DC3D1","bmi_desc":"需要提升体能健康增重，适当多吃高热量、高蛋白、高脂 肪饮食，多做力量运动如举重、俯卧撑、仰卧起坐等。<br>BMI：是指身体质量指数，国际上常用的衡量人体胖瘦程 度以及是否健康的一个标准。","body_fat_per":"10.50","body_fat_per_1":11,"body_fat_per_1_str":"偏低","body_fat_per_2":21,"body_fat_per_2_str":"标准","body_fat_per_3":26,"body_fat_per_3_str":"偏高","body_fat_per_4_str":"严重偏高","body_fat_per_str":"偏低","body_fat_per_color":"#0DC3D1","body_fat_per_desc":"当身体摄取到优质营养，并且令到小肠绒毛正常运作，就 可以达到正常的脂肪比例。为了增重，食物最好以易消 化、高蛋白、高热量为原则。","body_water_per":"64.60","body_water_per_1":55,"body_water_per_1_str":"偏低","body_water_per_2":65,"body_water_per_2_str":"标准","body_water_per_3_str":"充足","body_water_per_str":"标准","body_water_per_color":"#1bc050","body_water_per_desc":"身体水分率处于标准值，适量饮水，适当运动，均衡饮 食，保持身体水分的平衡。","skeletal_muscle_rate":"57.80","skeletal_muscle_rate_1":49,"skeletal_muscle_rate_1_str":"偏低","skeletal_muscle_rate_2":59,"skeletal_muscle_rate_2_str":"标准","skeletal_muscle_rate_3_str":"偏高","skeletal_muscle_rate_str":"标准","skeletal_muscle_rate_color":"#1bc050","skeletal_muscle_rate_desc":"您的骨胳肌比率处于标准范围。运动量过少或者节食会导 致肌肉流失，请保持适当的运动量和合理的饮食。<br>骨骼肌率：人体有多个肌肉组成，其中骨胳肌是可以通过 锻炼增加的肌肉。","muscle_mass":"65.34","muscle_mass_1":"49.4","muscle_mass_1_str":"偏低","muscle_mass_2":"59.4","muscle_mass_2_str":"标准","muscle_mass_3_str":"充足","muscle_mass_str":"充足","muscle_mass_color":"#1bc050","muscle_mass_desc":"您的肌肉比较发达，继续保持。","bone_mass":"3.44","bone_mass_1":"3.0","bone_mass_1_str":"偏低","bone_mass_2":"3.4","bone_mass_2_str":"标准","bone_mass_3_str":"偏高","bone_mass_str":"偏高","bone_mass_color":"#FFAD3E","bone_mass_desc":"您的骨量水平偏高。说明骨骼中包含的钙等无机盐的含量 非常充分，但要注意防范肾结石、低血压的风险，尽量避 免高钙摄入。","ffm":"68.78","ffm_str":"标准","ffm_color":"#1bc050","ffm_desc":"去脂体重是指除脂肪以外身体其他成分的重量，肌肉是其 中的主要部分。通过该指标可以看出您锻炼的效果，也可 以看出您减肥的潜力哦！","protein":"20.41","protein_1":"16","protein_1_str":"偏低","protein_2":"18","protein_2_str":"标准","protein_3_str":"充足","protein_str":"充足","protein_color":"#1bc050","protein_desc":"蛋白质比例充足。<br>蛋白质:生命的物质基础，是构成细胞的基本有机物。","subcutaneous_fat":"9.90","subcutaneous_fat_1":"8.6","subcutaneous_fat_1_str":"偏低","subcutaneous_fat_2":"16.7","subcutaneous_fat_2_str":"标准","subcutaneous_fat_3_str":"偏高","subcutaneous_fat_str":"标准","subcutaneous_fat_color":"#1bc050","subcutaneous_fat_desc":"您的皮下脂肪率处于标准范围。适当的运动量和合理的饮 食就能保持适量的皮下脂肪。","visceral_fat_grade":1,"visceral_fat_grade_1":"9","visceral_fat_grade_1_str":"标准","visceral_fat_grade_2":"14","visceral_fat_grade_2_str":"偏高","visceral_fat_grade_3_str":"严重偏高","visceral_fat_grade_str":"标准","visceral_fat_grade_color":"#1bc050","visceral_fat_grade_desc":"内脏脂肪指数标准，暂时没有太大风险。","body_age":29,"body_age_str":"不达标","body_age_color":"#FFAD3E","body_age_desc":"您还有年轻空间，加油！","shape":"3.0","shape_str":"偏瘦型","shape_color":"#FFAD3E","shape_desc":"偏瘦型","basal_metabolism":1855,"basal_metabolism_str":"达标","basal_metabolism_color":"#1bc050","basal_metabolism_desc":"您的标准基础代谢率为1844.4 kcal，处于达标状态。保持基 础代谢率最有效的方式是每天都进行适量的运动。","ideal_visual_weight":95.4,"ideal_visual_weight_str":"标准","ideal_visual_weight_color":"#1bc050","ideal_visual_weight_desc":"根据您的身高和年龄，您的理想视觉体重是95.4 kg","fat_control":"3.78","fat_control_str":"不达标","fat_control_color":"#FFAD3E","fat_control_desc":"根据健康的脂肪控制方式，建议您增加 /减少3.78 kg脂肪。","inorganic_salt_status_str":"偏高","inorganic_salt_status_color":"#FFAD3E","inorganic_salt_status_desc":"您的骨量水平偏高。说明骨骼中包含的钙等无机盐的含量 非常充分，但要注意防范肾结石、低血压的风险，尽量避 免高钙摄入。","muscle_ratio":"85.02","muscle_ratio_1":"3796.39","muscle_ratio_1_str":"缺乏","muscle_ratio_2":"4564.89","muscle_ratio_2_str":"正常","muscle_ratio_3_str":"充足","muscle_ratio_str":"缺乏","muscle_ratio_color":"#0DC3D1","muscle_ratio_desc":"您的肌肉率为85.02% ，处于缺乏状态，拥有更多的肌肉能 让您展示更好体型。","muscle_control":"7.57","muscle_control_str":"不达标","muscle_control_color":"#FFAD3E","muscle_control_desc":"根据健康的肌肉控制方式，建议您增加 /减少7.57  kg肌肉。","standard_weight":"88.20","standard_weight_str":"标准","standard_weight_color":"#1bc050","standard_weight_desc":"根据您的身高和年龄，您的标准体重是88.20 。","water_content":"49.65","water_content_1":"55","water_content_1_str":"偏低","water_content_2":"65","water_content_2_str":"标准","water_content_3_str":"充足","water_content_str":"偏低","water_content_color":"#0DC3D1","water_content_desc":"体水分率偏低，规律的饮食习惯和每天喝足8杯水可以维 持正常的体水分水平，充足的水分可以促进代谢，带走废 物和身体毒素。","weight_control":"11.35","weight_control_str":"不达标","weight_control_color":"#FFAD3E","weight_control_desc":"根据健康的体重控制方式，建议您增加 /减少11.35 kg体重。","obesity_1":"15","obesity_1_str":"标准","obesity_2":"32","obesity_2_str":"超重","obesity_3":"52","obesity_3_str":"轻度肥胖","obesity_4":"74","obesity_4_str":"中度肥胖","obesity_5_str":"严重肥胖","obesity":"-12.87","obesity_str":"标准","obesity_color":"#1bc050","obesity_desc":"判断是否肥胖的指标","protein_quality":"15.69","protein_quality_1":"12.30","protein_quality_1_str":"偏低","protein_quality_2":"13.83","protein_quality_2_str":"标准","protein_quality_3_str":"充足","protein_quality_str":"充足","protein_quality_color":"#1bc050","protein_quality_desc":"您的蛋白质充足，继续保持。","fat_weight":"8.07","fat_weight_1":"8.45","fat_weight_1_str":"偏低","fat_weight_2":"16.14","fat_weight_2_str":"标准","fat_weight_3":"19.98","fat_weight_3_str":"偏高","fat_weight_4_str":"严重偏高","fat_weight_str":"偏低","fat_weight_color":"#0DC3D1","fat_weight_desc":"体内所含脂肪的重量。","bf_record":"79.50","bf_record_str":"79.50","bf_record_color":"#FFAD3E","bf_record_desc":"您正在远离男神的体质了，运动起来吧！","birth":"1991-11-11","sex":1,"height":206,"add_date":"2019-11-27 15:50"},{"weight":"76.85","weight_1":"89.60","weight_1_str":"严重偏低","weight_2":"100.80","weight_2_str":"偏低","weight_3":"123.20","weight_3_str":"标准","weight_4":"134.40","weight_4_str":"偏高","weight_5_str":"严重偏高","weight_str":"严重偏低","weight_color":"#3E9FFF","weight_desc":"长期体重过轻会导致一系统列问题，如脱发、厌食症等， 身体机能会下降，需要加强营养，多吃高蛋白食物，摄入 更多的热量以增加体重。 偏低 体重偏低，身体消瘦，建议加","bmi":"13.30","bmi_1":18.5,"bmi_1_str":"偏低","bmi_2":25,"bmi_2_str":"标准","bmi_3_str":"偏高","bmi_str":"偏低","bmi_color":"#0DC3D1","bmi_desc":"需要提升体能健康增重，适当多吃高热量、高蛋白、高脂 肪饮食，多做力量运动如举重、俯卧撑、仰卧起坐等。<br>BMI：是指身体质量指数，国际上常用的衡量人体胖瘦程 度以及是否健康的一个标准。","body_fat_per":"5.10","body_fat_per_1":11,"body_fat_per_1_str":"偏低","body_fat_per_2":21,"body_fat_per_2_str":"标准","body_fat_per_3":26,"body_fat_per_3_str":"偏高","body_fat_per_4_str":"严重偏高","body_fat_per_str":"偏低","body_fat_per_color":"#0DC3D1","body_fat_per_desc":"当身体摄取到优质营养，并且令到小肠绒毛正常运作，就 可以达到正常的脂肪比例。为了增重，食物最好以易消 化、高蛋白、高热量为原则。","body_water_per":"77.40","body_water_per_1":55,"body_water_per_1_str":"偏低","body_water_per_2":65,"body_water_per_2_str":"标准","body_water_per_3_str":"充足","body_water_per_str":"充足","body_water_per_color":"#1bc050","body_water_per_desc":"身体水分含量高，细胞活性高。充足的水分能帮助您更好 地消化食物和吸收养分，并促进代谢，带走废物和毒素。","skeletal_muscle_rate":"69.30","skeletal_muscle_rate_1":49,"skeletal_muscle_rate_1_str":"偏低","skeletal_muscle_rate_2":59,"skeletal_muscle_rate_2_str":"标准","skeletal_muscle_rate_3_str":"偏高","skeletal_muscle_rate_str":"偏高","skeletal_muscle_rate_color":"#FFAD3E","skeletal_muscle_rate_desc":"如果脂肪比例正常，您是一个比较喜欢运动的人，适当的 骨胳肌比率能够显示您健壮的体型，但过高的骨胳肌比率 可能会影响您的灵活性。如果脂肪比例偏低，您的身材可 能偏瘦，平衡身体各项参数，您就能拥有健康标准的身 材。<br>骨骼肌率：人体有多个肌肉组成，其中骨胳肌是可以通过 锻炼增加的肌肉。","muscle_mass":"68.81","muscle_mass_1":"49.4","muscle_mass_1_str":"偏低","muscle_mass_2":"59.4","muscle_mass_2_str":"标准","muscle_mass_3_str":"充足","muscle_mass_str":"充足","muscle_mass_color":"#1bc050","muscle_mass_desc":"您的肌肉比较发达，继续保持。","bone_mass":"4.12","bone_mass_1":"3.0","bone_mass_1_str":"偏低","bone_mass_2":"3.4","bone_mass_2_str":"标准","bone_mass_3_str":"偏高","bone_mass_str":"偏高","bone_mass_color":"#FFAD3E","bone_mass_desc":"您的骨量水平偏高。说明骨骼中包含的钙等无机盐的含量 非常充分，但要注意防范肾结石、低血压的风险，尽量避 免高钙摄入。","ffm":"82.43","ffm_str":"标准","ffm_color":"#1bc050","ffm_desc":"去脂体重是指除脂肪以外身体其他成分的重量，肌肉是其 中的主要部分。通过该指标可以看出您锻炼的效果，也可 以看出您减肥的潜力哦！","protein":"12.09","protein_1":"16","protein_1_str":"偏低","protein_2":"18","protein_2_str":"标准","protein_3_str":"充足","protein_str":"偏低","protein_color":"#0DC3D1","protein_desc":"蛋白质不足会引起基础代谢减少，也会引起肌肉的数量减 少。坚持长期运动，适当提高肌肉比例，辅助于蛋白质的 补充，可以提升蛋白质比例。<br>蛋白质:生命的物质基础，是构成细胞的基本有机物。","subcutaneous_fat":"4.70","subcutaneous_fat_1":"8.6","subcutaneous_fat_1_str":"偏低","subcutaneous_fat_2":"16.7","subcutaneous_fat_2_str":"标准","subcutaneous_fat_3_str":"偏高","subcutaneous_fat_str":"偏低","subcutaneous_fat_color":"#0DC3D1","subcutaneous_fat_desc":"适量的皮下脂肪能够保护内脏和抵御寒冷，适量增加高蛋 白、高热量食物可以增加脂肪。","visceral_fat_grade":1,"visceral_fat_grade_1":"9","visceral_fat_grade_1_str":"标准","visceral_fat_grade_2":"14","visceral_fat_grade_2_str":"偏高","visceral_fat_grade_3_str":"严重偏高","visceral_fat_grade_str":"标准","visceral_fat_grade_color":"#1bc050","visceral_fat_grade_desc":"内脏脂肪指数标准，暂时没有太大风险。","body_age":36,"body_age_str":"不达标","body_age_color":"#FFAD3E","body_age_desc":"您还有年轻空间，加油！","shape":"3.0","shape_str":"偏瘦型","shape_color":"#FFAD3E","shape_desc":"偏瘦型","basal_metabolism":1945,"basal_metabolism_str":"达标","basal_metabolism_color":"#1bc050","basal_metabolism_desc":"您的标准基础代谢率为1844.4 kcal，处于达标状态。保持基 础代谢率最有效的方式是每天都进行适量的运动。","ideal_visual_weight":126,"ideal_visual_weight_str":"标准","ideal_visual_weight_color":"#1bc050","ideal_visual_weight_desc":"根据您的身高和年龄，您的理想视觉体重是126 kg","fat_control":"11.72","fat_control_str":"不达标","fat_control_color":"#FFAD3E","fat_control_desc":"根据健康的脂肪控制方式，建议您增加 /减少11.72 kg脂肪。","inorganic_salt_status_str":"偏高","inorganic_salt_status_color":"#FFAD3E","inorganic_salt_status_desc":"您的骨量水平偏高。说明骨骼中包含的钙等无机盐的含量 非常充分，但要注意防范肾结石、低血压的风险，尽量避 免高钙摄入。","muscle_ratio":"89.54","muscle_ratio_1":"3796.39","muscle_ratio_1_str":"缺乏","muscle_ratio_2":"4564.89","muscle_ratio_2_str":"正常","muscle_ratio_3_str":"充足","muscle_ratio_str":"缺乏","muscle_ratio_color":"#0DC3D1","muscle_ratio_desc":"您的肌肉率为89.54% ，处于缺乏状态，拥有更多的肌肉能 让您展示更好体型。","muscle_control":"23.43","muscle_control_str":"不达标","muscle_control_color":"#FFAD3E","muscle_control_desc":"根据健康的肌肉控制方式，建议您增加 /减少23.43  kg肌肉。","standard_weight":"112.00","standard_weight_str":"标准","standard_weight_color":"#1bc050","standard_weight_desc":"根据您的身高和年龄，您的标准体重是112.00 。","water_content":"59.48","water_content_1":"55","water_content_1_str":"偏低","water_content_2":"65","water_content_2_str":"标准","water_content_3_str":"充足","water_content_str":"标准","water_content_color":"#1bc050","water_content_desc":"身体水分率处于标准值，适量饮水，适当运动，均衡饮 食，保持身体水分的平衡。","weight_control":"35.15","weight_control_str":"不达标","weight_control_color":"#FFAD3E","weight_control_desc":"根据健康的体重控制方式，建议您增加 /减少35.15 kg体重。","obesity_1":"15","obesity_1_str":"标准","obesity_2":"32","obesity_2_str":"超重","obesity_3":"52","obesity_3_str":"轻度肥胖","obesity_4":"74","obesity_4_str":"中度肥胖","obesity_5_str":"严重肥胖","obesity":"-31.38","obesity_str":"标准","obesity_color":"#1bc050","obesity_desc":"判断是否肥胖的指标","protein_quality":"9.29","protein_quality_1":"12.30","protein_quality_1_str":"偏低","protein_quality_2":"13.83","protein_quality_2_str":"标准","protein_quality_3_str":"充足","protein_quality_str":"偏低","protein_quality_color":"#0DC3D1","protein_quality_desc":"您缺少足够的蛋白质，需要加强营养，增加蛋白质。","fat_weight":"3.92","fat_weight_1":"8.45","fat_weight_1_str":"偏低","fat_weight_2":"16.14","fat_weight_2_str":"标准","fat_weight_3":"19.98","fat_weight_3_str":"偏高","fat_weight_4_str":"严重偏高","fat_weight_str":"偏低","fat_weight_color":"#0DC3D1","fat_weight_desc":"体内所含脂肪的重量。","bf_record":"46.80","bf_record_str":"46.80","bf_record_color":"#FFAD3E","bf_record_desc":"您的身体状况简直不能忍...接受现实还是改变自己,做出 选择吧！！","birth":"1991-11-11","sex":1,"height":240,"add_date":"2019-11-27 15:50"},{"weight":"76.85","weight_1":"50.96","weight_1_str":"严重偏低","weight_2":"57.33","weight_2_str":"偏低","weight_3":"70.07","weight_3_str":"标准","weight_4":"76.44","weight_4_str":"偏高","weight_5_str":"严重偏高","weight_str":"严重偏高","weight_color":"#E13F14","weight_desc":"体重严重超标，建议低脂、低胆固醇、高纤维膳食，补充 多种维生素，增加运动量进行体重控制。","bmi":"26.30","bmi_1":18.5,"bmi_1_str":"偏低","bmi_2":25,"bmi_2_str":"标准","bmi_3_str":"偏高","bmi_str":"偏高","bmi_color":"#FFAD3E","bmi_desc":"BMI超标，建议选择比较健康的方法减重，如控制饮食、 改变不良生活习惯和参加跑步、跳绳、打篮球、踢足球等 消耗体能的运动。<br>BMI：是指身体质量指数，国际上常用的衡量人体胖瘦程 度以及是否健康的一个标准。","body_fat_per":"26.50","body_fat_per_1":11,"body_fat_per_1_str":"偏低","body_fat_per_2":21,"body_fat_per_2_str":"标准","body_fat_per_3":26,"body_fat_per_3_str":"偏高","body_fat_per_4_str":"严重偏高","body_fat_per_str":"严重偏高","body_fat_per_color":"#E13F14","body_fat_per_desc":"您的体内囤积了太多脂肪，必须检测血压、血糖、肝功能 等情况，是否潜藏危害。赶快开始您的减肥大战，坚持饮 食控制、运动及改变生活方式。","body_water_per":"53.10","body_water_per_1":55,"body_water_per_1_str":"偏低","body_water_per_2":65,"body_water_per_2_str":"标准","body_water_per_3_str":"充足","body_water_per_str":"偏低","body_water_per_color":"#0DC3D1","body_water_per_desc":"体水分率偏低，规律的饮食习惯和每天喝足8杯水可以维 持正常的体水分水平，充足的水分可以促进代谢，带走废 物和身体毒素。","skeletal_muscle_rate":"47.50","skeletal_muscle_rate_1":49,"skeletal_muscle_rate_1_str":"偏低","skeletal_muscle_rate_2":59,"skeletal_muscle_rate_2_str":"标准","skeletal_muscle_rate_3_str":"偏高","skeletal_muscle_rate_str":"偏低","skeletal_muscle_rate_color":"#0DC3D1","skeletal_muscle_rate_desc":"您的骨胳肌比率低于理想范围，跟多静态活动、不运动有 关，会导致基础代谢率降低，腰酸背痛，力量下降，外在 表现是发胖，也容易诱发心血管疾病。<br>骨骼肌率：人体有多个肌肉组成，其中骨胳肌是可以通过 锻炼增加的肌肉。","muscle_mass":"53.66","muscle_mass_1":"49.4","muscle_mass_1_str":"偏低","muscle_mass_2":"59.4","muscle_mass_2_str":"标准","muscle_mass_3_str":"充足","muscle_mass_str":"标准","muscle_mass_color":"#1bc050","muscle_mass_desc":"您的肌肉比较发达，继续保持。","bone_mass":"2.82","bone_mass_1":"3.0","bone_mass_1_str":"偏低","bone_mass_2":"3.4","bone_mass_2_str":"标准","bone_mass_3_str":"偏高","bone_mass_str":"偏低","bone_mass_color":"#0DC3D1","bone_mass_desc":"您的骨量水平偏低。长期低钙饮食、缺乏运动、过度减肥 等都可能引起骨量偏低，多吃含钙高的食物，多晒太阳， 多运动及时补钙。","ffm":"56.49","ffm_str":"标准","ffm_color":"#1bc050","ffm_desc":"去脂体重是指除脂肪以外身体其他成分的重量，肌肉是其 中的主要部分。通过该指标可以看出您锻炼的效果，也可 以看出您减肥的潜力哦！","protein":"16.75","protein_1":"16","protein_1_str":"偏低","protein_2":"18","protein_2_str":"标准","protein_3_str":"充足","protein_str":"标准","protein_color":"#1bc050","protein_desc":"您的蛋白质处于标准水平。<br>蛋白质:生命的物质基础，是构成细胞的基本有机物。","subcutaneous_fat":"23.60","subcutaneous_fat_1":"8.6","subcutaneous_fat_1_str":"偏低","subcutaneous_fat_2":"16.7","subcutaneous_fat_2_str":"标准","subcutaneous_fat_3_str":"偏高","subcutaneous_fat_str":"偏高","subcutaneous_fat_color":"#FFAD3E","subcutaneous_fat_desc":"皮下脂肪过多是外表肥胖的主要原因，除了有氧减脂以 外，多进行增肌训练，肌肉的增加可以让您拥有更完美的 体型。","visceral_fat_grade":9,"visceral_fat_grade_1":"9","visceral_fat_grade_1_str":"标准","visceral_fat_grade_2":"14","visceral_fat_grade_2_str":"偏高","visceral_fat_grade_3_str":"严重偏高","visceral_fat_grade_str":"标准","visceral_fat_grade_color":"#1bc050","visceral_fat_grade_desc":"内脏脂肪指数标准，暂时没有太大风险。","body_age":35,"body_age_str":"不达标","body_age_color":"#FFAD3E","body_age_desc":"您还有年轻空间，加油！","shape":"6.0","shape_str":"肥胖型","shape_color":"#FFAD3E","shape_desc":"肥胖型","basal_metabolism":1590,"basal_metabolism_str":"不达标","basal_metabolism_color":"#FFAD3E","basal_metabolism_desc":"您的标准基础代谢率为1713.755 kcal，目前处于未达标状态。 持续轻量运动能够提高身体的基础代谢率，而节食基础代 谢会大幅下降。","ideal_visual_weight":63.9,"ideal_visual_weight_str":"标准","ideal_visual_weight_color":"#1bc050","ideal_visual_weight_desc":"根据您的身高和年龄，您的理想视觉体重是63.9 kg","fat_control":"-8.84","fat_control_str":"不达标","fat_control_color":"#FFAD3E","fat_control_desc":"根据健康的脂肪控制方式，建议您增加 /减少-8.84 kg脂肪。","inorganic_salt_status_str":"偏低","inorganic_salt_status_color":"#0DC3D1","inorganic_salt_status_desc":"您的骨量水平偏低。长期低钙饮食、缺乏运动、过度减肥 等都可能引起骨量偏低，多吃含钙高的食物，多晒太阳， 多运动及时补钙。","muscle_ratio":"69.82","muscle_ratio_1":"3796.39","muscle_ratio_1_str":"缺乏","muscle_ratio_2":"4564.89","muscle_ratio_2_str":"正常","muscle_ratio_3_str":"充足","muscle_ratio_str":"缺乏","muscle_ratio_color":"#0DC3D1","muscle_ratio_desc":"您的肌肉率为69.82% ，处于缺乏状态，拥有更多的肌肉能 让您展示更好体型。","muscle_control":"2.95","muscle_control_str":"不达标","muscle_control_color":"#FFAD3E","muscle_control_desc":"根据健康的肌肉控制方式，建议您增加 /减少2.95  kg肌肉。","standard_weight":"63.70","standard_weight_str":"标准","standard_weight_color":"#1bc050","standard_weight_desc":"根据您的身高和年龄，您的标准体重是63.70 。","water_content":"40.81","water_content_1":"55","water_content_1_str":"偏低","water_content_2":"65","water_content_2_str":"标准","water_content_3_str":"充足","water_content_str":"偏低","water_content_color":"#0DC3D1","water_content_desc":"体水分率偏低，规律的饮食习惯和每天喝足8杯水可以维 持正常的体水分水平，充足的水分可以促进代谢，带走废 物和身体毒素。","weight_control":"-5.89","weight_control_str":"不达标","weight_control_color":"#FFAD3E","weight_control_desc":"根据健康的体重控制方式，建议您增加 /减少-5.89 kg体重。","obesity_1":"15","obesity_1_str":"标准","obesity_2":"32","obesity_2_str":"超重","obesity_3":"52","obesity_3_str":"轻度肥胖","obesity_4":"74","obesity_4_str":"中度肥胖","obesity_5_str":"严重肥胖","obesity":"20.64","obesity_str":"超重","obesity_color":"#FFAD3E","obesity_desc":"判断是否肥胖的指标","protein_quality":"12.87","protein_quality_1":"12.30","protein_quality_1_str":"偏低","protein_quality_2":"13.83","protein_quality_2_str":"标准","protein_quality_3_str":"充足","protein_quality_str":"标准","protein_quality_color":"#1bc050","protein_quality_desc":"您的蛋白质正常，继续保持。","fat_weight":"20.37","fat_weight_1":"8.45","fat_weight_1_str":"偏低","fat_weight_2":"16.14","fat_weight_2_str":"标准","fat_weight_3":"19.98","fat_weight_3_str":"偏高","fat_weight_4_str":"严重偏高","fat_weight_str":"严重偏高","fat_weight_color":"#E13F14","fat_weight_desc":"体内所含脂肪的重量。","bf_record":"77.00","bf_record_str":"77.00","bf_record_color":"#FFAD3E","bf_record_desc":"您正在远离男神的体质了，运动起来吧！","birth":"1985-11-11","sex":1,"height":171,"add_date":"2019-11-27 15:48"},{"weight":"76.85","weight_1":"32.90","weight_1_str":"严重偏低","weight_2":"37.02","weight_2_str":"偏低","weight_3":"45.24","weight_3_str":"标准","weight_4":"49.36","weight_4_str":"偏高","weight_5_str":"严重偏高","weight_str":"严重偏高","weight_color":"#E13F14","weight_desc":"体重严重超标，建议低脂、低胆固醇、高纤维膳食，补充 多种维生素，增加运动量进行体重控制。","bmi":"35.60","bmi_1":18.5,"bmi_1_str":"偏低","bmi_2":25,"bmi_2_str":"标准","bmi_3_str":"偏高","bmi_str":"偏高","bmi_color":"#FFAD3E","bmi_desc":"BMI超标，建议选择比较健康的方法减重，如控制饮食、 改变不良生活习惯和参加跑步、跳绳、打篮球、踢足球等 消耗体能的运动。<br>BMI：是指身体质量指数，国际上常用的衡量人体胖瘦程 度以及是否健康的一个标准。","body_fat_per":"42.30","body_fat_per_1":21,"body_fat_per_1_str":"偏低","body_fat_per_2":31,"body_fat_per_2_str":"标准","body_fat_per_3":36,"body_fat_per_3_str":"偏高","body_fat_per_4_str":"严重偏高","body_fat_per_str":"严重偏高","body_fat_per_color":"#E13F14","body_fat_per_desc":"您的体内囤积了太多脂肪，必须检测血压、血糖、肝功能 等情况，是否潜藏危害。赶快开始您的减肥大战，坚持饮 食控制、运动及改变生活方式。","body_water_per":"39.60","body_water_per_1":45,"body_water_per_1_str":"偏低","body_water_per_2":60,"body_water_per_2_str":"标准","body_water_per_3_str":"充足","body_water_per_str":"偏低","body_water_per_color":"#0DC3D1","body_water_per_desc":"体水分率偏低，规律的饮食习惯和每天喝足8杯水可以维 持正常的体水分水平，充足的水分可以促进代谢，带走废 物和身体毒素。","skeletal_muscle_rate":"33.60","skeletal_muscle_rate_1":40,"skeletal_muscle_rate_1_str":"偏低","skeletal_muscle_rate_2":50,"skeletal_muscle_rate_2_str":"标准","skeletal_muscle_rate_3_str":"偏高","skeletal_muscle_rate_str":"偏低","skeletal_muscle_rate_color":"#0DC3D1","skeletal_muscle_rate_desc":"您的骨胳肌比率低于理想范围，跟多静态活动、不运动有 关，会导致基础代谢率降低，腰酸背痛，力量下降，外在 表现是发胖，也容易诱发心血管疾病。<br>骨骼肌率：人体有多个肌肉组成，其中骨胳肌是可以通过 锻炼增加的肌肉。","muscle_mass":"41.68","muscle_mass_1":"29.1","muscle_mass_1_str":"偏低","muscle_mass_2":"34.7","muscle_mass_2_str":"标准","muscle_mass_3_str":"充足","muscle_mass_str":"充足","muscle_mass_color":"#1bc050","muscle_mass_desc":"您的肌肉比较发达，继续保持。","bone_mass":"2.66","bone_mass_1":"2.3","bone_mass_1_str":"偏低","bone_mass_2":"2.7","bone_mass_2_str":"标准","bone_mass_3_str":"偏高","bone_mass_str":"标准","bone_mass_color":"#1bc050","bone_mass_desc":"您的骨量水平标准。骨量在短期内不会出现明显的变化， 您只要保证健康的饮食和适当的锻炼，就可以维持稳定健 康的骨量水平。","ffm":"44.35","ffm_str":"标准","ffm_color":"#1bc050","ffm_desc":"去脂体重是指除脂肪以外身体其他成分的重量，肌肉是其 中的主要部分。通过该指标可以看出您锻炼的效果，也可 以看出您减肥的潜力哦！","protein":"12.52","protein_1":"14","protein_1_str":"偏低","protein_2":"16","protein_2_str":"标准","protein_3_str":"充足","protein_str":"偏低","protein_color":"#0DC3D1","protein_desc":"蛋白质不足会引起基础代谢减少，也会引起肌肉的数量减 少。坚持长期运动，适当提高肌肉比例，辅助于蛋白质的 补充，可以提升蛋白质比例。<br>蛋白质:生命的物质基础，是构成细胞的基本有机物。","subcutaneous_fat":"36.40","subcutaneous_fat_1":"18.5","subcutaneous_fat_1_str":"偏低","subcutaneous_fat_2":"26.7","subcutaneous_fat_2_str":"标准","subcutaneous_fat_3_str":"偏高","subcutaneous_fat_str":"偏高","subcutaneous_fat_color":"#FFAD3E","subcutaneous_fat_desc":"皮下脂肪过多是外表肥胖的主要原因，除了有氧减脂以 外，多进行增肌训练，肌肉的增加可以让您拥有更完美的 体型。","visceral_fat_grade":17,"visceral_fat_grade_1":"9","visceral_fat_grade_1_str":"标准","visceral_fat_grade_2":"14","visceral_fat_grade_2_str":"偏高","visceral_fat_grade_3_str":"严重偏高","visceral_fat_grade_str":"严重偏高","visceral_fat_grade_color":"#E13F14","visceral_fat_grade_desc":"内脏脂肪指数危险，罹患心脏病、高血压、高血脂和Ⅱ型 糖尿病风险大，您迫切需要控制体重、积极运动和限制饮 食。","body_age":41,"body_age_str":"不达标","body_age_color":"#FFAD3E","body_age_desc":"您还有年轻空间，加油！","shape":"6.0","shape_str":"肥胖型","shape_color":"#FFAD3E","shape_desc":"肥胖型","basal_metabolism":1327,"basal_metabolism_str":"不达标","basal_metabolism_color":"#FFAD3E","basal_metabolism_desc":"您的标准基础代谢率为1667.645 kcal，目前处于未达标状态。持续轻量运动能够提高身体的基础代谢率，而节食基础代谢会大幅下降。","ideal_visual_weight":37.6,"ideal_visual_weight_str":"标准","ideal_visual_weight_color":"#1bc050","ideal_visual_weight_desc":"根据您的身高和年龄，您的理想视觉体重是37.6 kg","fat_control":"-15.60","fat_control_str":"不达标","fat_control_color":"#FFAD3E","fat_control_desc":"根据健康的脂肪控制方式，建议您增加 /减少-15.60 kg脂肪。","inorganic_salt_status_str":"标准","inorganic_salt_status_color":"#1bc050","inorganic_salt_status_desc":"您的骨量水平标准。骨量在短期内不会出现明显的变化， 您只要保证健康的饮食和适当的锻炼，就可以维持稳定健 康的骨量水平。","muscle_ratio":"54.24","muscle_ratio_1":"2236.34","muscle_ratio_1_str":"缺乏","muscle_ratio_2":"2666.70","muscle_ratio_2_str":"正常","muscle_ratio_3_str":"充足","muscle_ratio_str":"缺乏","muscle_ratio_color":"#0DC3D1","muscle_ratio_desc":"您的肌肉率为54.24% ，处于缺乏状态，拥有更多的肌肉能 让您展示更好体型。","muscle_control":"5.20","muscle_control_str":"不达标","muscle_control_color":"#FFAD3E","muscle_control_desc":"根据健康的肌肉控制方式，建议您增加 /减少5.20  kg肌肉。","standard_weight":"41.13","standard_weight_str":"标准","standard_weight_color":"#1bc050","standard_weight_desc":"根据您的身高和年龄，您的标准体重是41.13 。","water_content":"30.43","water_content_1":"45","water_content_1_str":"偏低","water_content_2":"60","water_content_2_str":"标准","water_content_3_str":"充值","water_content_str":"偏低","water_content_color":"#0DC3D1","water_content_desc":"体水分率偏低，规律的饮食习惯和每天喝足8杯水可以维 持正常的体水分水平，充足的水分可以促进代谢，带走废 物和身体毒素。","weight_control":"-10.40","weight_control_str":"不达标","weight_control_color":"#FFAD3E","weight_control_desc":"根据健康的体重控制方式，建议您增加 /减少-10.40 kg体重。","obesity_1":"15","obesity_1_str":"标准","obesity_2":"32","obesity_2_str":"超重","obesity_3":"52","obesity_3_str":"轻度肥胖","obesity_4":"74","obesity_4_str":"中度肥胖","obesity_5_str":"严重肥胖","obesity":"86.85","obesity_str":"严重肥胖","obesity_color":"#FFAD3E","obesity_desc":"判断是否肥胖的指标","protein_quality":"9.62","protein_quality_1":"10.76","protein_quality_1_str":"偏低","protein_quality_2":"12.30","protein_quality_2_str":"标准","protein_quality_3_str":"充足","protein_quality_str":"偏低","protein_quality_color":"#0DC3D1","protein_quality_desc":"您缺少足够的蛋白质，需要加强营养，增加蛋白质。","fat_weight":"32.51","fat_weight_1":"16.14","fat_weight_1_str":"偏低","fat_weight_2":"23.82","fat_weight_2_str":"标准","fat_weight_3":"27.67","fat_weight_3_str":"偏高","fat_weight_4_str":"严重偏高","fat_weight_str":"严重偏高","fat_weight_color":"#E13F14","fat_weight_desc":"体内所含脂肪的重量。","bf_record":"52.40","bf_record_str":"52.40","bf_record_color":"#FFAD3E","bf_record_desc":"您的身体状况简直不能忍...接受现实还是改变自己,做出 选择吧！！","birth":"1985-11-11","sex":2,"height":147,"add_date":"2019-11-27 15:48"},{"weight":"76.85","weight_1":"37.52","weight_1_str":"严重偏低","weight_2":"42.21","weight_2_str":"偏低","weight_3":"51.59","weight_3_str":"标准","weight_4":"56.28","weight_4_str":"偏高","weight_5_str":"严重偏高","weight_str":"严重偏高","weight_color":"#E13F14","weight_desc":"体重严重超标，建议低脂、低胆固醇、高纤维膳食，补充 多种维生素，增加运动量进行体重控制。","bmi":"35.60","bmi_1":18.5,"bmi_1_str":"偏低","bmi_2":25,"bmi_2_str":"标准","bmi_3_str":"偏高","bmi_str":"偏高","bmi_color":"#FFAD3E","bmi_desc":"BMI超标，建议选择比较健康的方法减重，如控制饮食、 改变不良生活习惯和参加跑步、跳绳、打篮球、踢足球等 消耗体能的运动。<br>BMI：是指身体质量指数，国际上常用的衡量人体胖瘦程 度以及是否健康的一个标准。","body_fat_per":"34.20","body_fat_per_1":11,"body_fat_per_1_str":"偏低","body_fat_per_2":21,"body_fat_per_2_str":"标准","body_fat_per_3":26,"body_fat_per_3_str":"偏高","body_fat_per_4_str":"严重偏高","body_fat_per_str":"严重偏高","body_fat_per_color":"#E13F14","body_fat_per_desc":"您的体内囤积了太多脂肪，必须检测血压、血糖、肝功能 等情况，是否潜藏危害。赶快开始您的减肥大战，坚持饮 食控制、运动及改变生活方式。","body_water_per":"47.50","body_water_per_1":55,"body_water_per_1_str":"偏低","body_water_per_2":65,"body_water_per_2_str":"标准","body_water_per_3_str":"充足","body_water_per_str":"偏低","body_water_per_color":"#0DC3D1","body_water_per_desc":"体水分率偏低，规律的饮食习惯和每天喝足8杯水可以维 持正常的体水分水平，充足的水分可以促进代谢，带走废 物和身体毒素。","skeletal_muscle_rate":"42.50","skeletal_muscle_rate_1":49,"skeletal_muscle_rate_1_str":"偏低","skeletal_muscle_rate_2":59,"skeletal_muscle_rate_2_str":"标准","skeletal_muscle_rate_3_str":"偏高","skeletal_muscle_rate_str":"偏低","skeletal_muscle_rate_color":"#0DC3D1","skeletal_muscle_rate_desc":"您的骨胳肌比率低于理想范围，跟多静态活动、不运动有 关，会导致基础代谢率降低，腰酸背痛，力量下降，外在 表现是发胖，也容易诱发心血管疾病。<br>骨骼肌率：人体有多个肌肉组成，其中骨胳肌是可以通过 锻炼增加的肌肉。","muscle_mass":"48.04","muscle_mass_1":38.5,"muscle_mass_1_str":"偏低","muscle_mass_2":46.5,"muscle_mass_2_str":"标准","muscle_mass_3_str":"充足","muscle_mass_str":"充足","muscle_mass_color":"#1bc050","muscle_mass_desc":"您的肌肉比较发达，继续保持。","bone_mass":"2.53","bone_mass_1":"3.0","bone_mass_1_str":"偏低","bone_mass_2":"3.4","bone_mass_2_str":"标准","bone_mass_3_str":"偏高","bone_mass_str":"偏低","bone_mass_color":"#0DC3D1","bone_mass_desc":"您的骨量水平偏低。长期低钙饮食、缺乏运动、过度减肥 等都可能引起骨量偏低，多吃含钙高的食物，多晒太阳， 多运动及时补钙。","ffm":"50.59","ffm_str":"标准","ffm_color":"#1bc050","ffm_desc":"去脂体重是指除脂肪以外身体其他成分的重量，肌肉是其 中的主要部分。通过该指标可以看出您锻炼的效果，也可 以看出您减肥的潜力哦！","protein":"14.98","protein_1":"16","protein_1_str":"偏低","protein_2":"18","protein_2_str":"标准","protein_3_str":"充足","protein_str":"偏低","protein_color":"#0DC3D1","protein_desc":"蛋白质不足会引起基础代谢减少，也会引起肌肉的数量减 少。坚持长期运动，适当提高肌肉比例，辅助于蛋白质的 补充，可以提升蛋白质比例。<br>蛋白质:生命的物质基础，是构成细胞的基本有机物。","subcutaneous_fat":"29.30","subcutaneous_fat_1":"8.6","subcutaneous_fat_1_str":"偏低","subcutaneous_fat_2":"16.7","subcutaneous_fat_2_str":"标准","subcutaneous_fat_3_str":"偏高","subcutaneous_fat_str":"偏高","subcutaneous_fat_color":"#FFAD3E","subcutaneous_fat_desc":"皮下脂肪过多是外表肥胖的主要原因，除了有氧减脂以 外，多进行增肌训练，肌肉的增加可以让您拥有更完美的 体型。","visceral_fat_grade":17,"visceral_fat_grade_1":"9","visceral_fat_grade_1_str":"标准","visceral_fat_grade_2":"14","visceral_fat_grade_2_str":"偏高","visceral_fat_grade_3_str":"严重偏高","visceral_fat_grade_str":"严重偏高","visceral_fat_grade_color":"#E13F14","visceral_fat_grade_desc":"内脏脂肪指数危险，罹患心脏病、高血压、高血脂和Ⅱ型 糖尿病风险大，您迫切需要控制体重、积极运动和限制饮 食。","body_age":27,"body_age_str":"不达标","body_age_color":"#FFAD3E","body_age_desc":"您还有年轻空间，加油！","shape":"6.0","shape_str":"肥胖型","shape_color":"#FFAD3E","shape_desc":"肥胖型","basal_metabolism":1462,"basal_metabolism_str":"不达标","basal_metabolism_color":"#FFAD3E","basal_metabolism_desc":"您的标准基础代谢率为1844.4 kcal，目前处于未达标状态。 持续轻量运动能够提高身体的基础代谢率，而节食基础代 谢会大幅下降。","ideal_visual_weight":42.300000000000004,"ideal_visual_weight_str":"标准","ideal_visual_weight_color":"#1bc050","ideal_visual_weight_desc":"根据您的身高和年龄，您的理想视觉体重是42.3 kg","fat_control":"-14.76","fat_control_str":"不达标","fat_control_color":"#FFAD3E","fat_control_desc":"根据健康的脂肪控制方式，建议您增加 /减少-14.76 kg脂肪。","inorganic_salt_status_str":"偏低","inorganic_salt_status_color":"#0DC3D1","inorganic_salt_status_desc":"您的骨量水平偏低。长期低钙饮食、缺乏运动、过度减肥 等都可能引起骨量偏低，多吃含钙高的食物，多晒太阳， 多运动及时补钙。","muscle_ratio":"62.51","muscle_ratio_1":"2958.72","muscle_ratio_1_str":"缺乏","muscle_ratio_2":"3573.52","muscle_ratio_2_str":"正常","muscle_ratio_3_str":"充足","muscle_ratio_str":"缺乏","muscle_ratio_color":"#0DC3D1","muscle_ratio_desc":"您的肌肉率为62.51% ，处于缺乏状态，拥有更多的肌肉能 让您展示更好体型。","muscle_control":"4.92","muscle_control_str":"不达标","muscle_control_color":"#FFAD3E","muscle_control_desc":"根据健康的肌肉控制方式，建议您增加 /减少4.92  kg肌肉。","standard_weight":"46.90","standard_weight_str":"标准","standard_weight_color":"#1bc050","standard_weight_desc":"根据您的身高和年龄，您的标准体重是46.90 。","water_content":"36.50","water_content_1":"55","water_content_1_str":"偏低","water_content_2":"65","water_content_2_str":"标准","water_content_3_str":"充足","water_content_str":"偏低","water_content_color":"#0DC3D1","water_content_desc":"体水分率偏低，规律的饮食习惯和每天喝足8杯水可以维 持正常的体水分水平，充足的水分可以促进代谢，带走废 物和身体毒素。","weight_control":"-9.84","weight_control_str":"不达标","weight_control_color":"#FFAD3E","weight_control_desc":"根据健康的体重控制方式，建议您增加 /减少-9.84 kg体重。","obesity_1":"15","obesity_1_str":"标准","obesity_2":"32","obesity_2_str":"超重","obesity_3":"52","obesity_3_str":"轻度肥胖","obesity_4":"74","obesity_4_str":"中度肥胖","obesity_5_str":"严重肥胖","obesity":"63.86","obesity_str":"中度肥胖","obesity_color":"#FFAD3E","obesity_desc":"判断是否肥胖的指标","protein_quality":"11.51","protein_quality_1":"12.30","protein_quality_1_str":"偏低","protein_quality_2":"13.83","protein_quality_2_str":"标准","protein_quality_3_str":"充足","protein_quality_str":"偏低","protein_quality_color":"#0DC3D1","protein_quality_desc":"您缺少足够的蛋白质，需要加强营养，增加蛋白质。","fat_weight":"26.28","fat_weight_1":"8.45","fat_weight_1_str":"偏低","fat_weight_2":"16.14","fat_weight_2_str":"标准","fat_weight_3":"19.98","fat_weight_3_str":"偏高","fat_weight_4_str":"严重偏高","fat_weight_str":"严重偏高","fat_weight_color":"#E13F14","fat_weight_desc":"体内所含脂肪的重量。","bf_record":"56.20","bf_record_str":"56.20","bf_record_color":"#FFAD3E","bf_record_desc":"您的身体状况简直不能忍...接受现实还是改变自己,做出 选择吧！！","birth":"1999-11-11","sex":1,"height":147,"add_date":"2019-11-27 15:47"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * weight : 76.85
             * weight_1 : 39.76
             * weight_1_str : 严重偏低
             * weight_2 : 44.73
             * weight_2_str : 偏低
             * weight_3 : 54.67
             * weight_3_str : 标准
             * weight_4 : 59.64
             * weight_4_str : 偏高
             * weight_5_str : 严重偏高
             * weight_str : 严重偏高
             * weight_color : #E13F14
             * weight_desc : 体重严重超标，建议低脂、低胆固醇、高纤维膳食，补充 多种维生素，增加运动量进行体重控制。
             * bmi : 33.70
             * bmi_1 : 18.5
             * bmi_1_str : 偏低
             * bmi_2 : 25
             * bmi_2_str : 标准
             * bmi_3_str : 偏高
             * bmi_str : 偏高
             * bmi_color : #FFAD3E
             * bmi_desc : BMI超标，建议选择比较健康的方法减重，如控制饮食、 改变不良生活习惯和参加跑步、跳绳、打篮球、踢足球等 消耗体能的运动。<br>BMI：是指身体质量指数，国际上常用的衡量人体胖瘦程 度以及是否健康的一个标准。
             * body_fat_per : 33.50
             * body_fat_per_1 : 11
             * body_fat_per_1_str : 偏低
             * body_fat_per_2 : 21
             * body_fat_per_2_str : 标准
             * body_fat_per_3 : 26
             * body_fat_per_3_str : 偏高
             * body_fat_per_4_str : 严重偏高
             * body_fat_per_str : 严重偏高
             * body_fat_per_color : #E13F14
             * body_fat_per_desc : 您的体内囤积了太多脂肪，必须检测血压、血糖、肝功能 等情况，是否潜藏危害。赶快开始您的减肥大战，坚持饮 食控制、运动及改变生活方式。
             * body_water_per : 48.00
             * body_water_per_1 : 55
             * body_water_per_1_str : 偏低
             * body_water_per_2 : 65
             * body_water_per_2_str : 标准
             * body_water_per_3_str : 充足
             * body_water_per_str : 偏低
             * body_water_per_color : #0DC3D1
             * body_water_per_desc : 体水分率偏低，规律的饮食习惯和每天喝足8杯水可以维 持正常的体水分水平，充足的水分可以促进代谢，带走废 物和身体毒素。
             * skeletal_muscle_rate : 43.00
             * skeletal_muscle_rate_1 : 49
             * skeletal_muscle_rate_1_str : 偏低
             * skeletal_muscle_rate_2 : 59
             * skeletal_muscle_rate_2_str : 标准
             * skeletal_muscle_rate_3_str : 偏高
             * skeletal_muscle_rate_str : 偏低
             * skeletal_muscle_rate_color : #0DC3D1
             * skeletal_muscle_rate_desc : 您的骨胳肌比率低于理想范围，跟多静态活动、不运动有 关，会导致基础代谢率降低，腰酸背痛，力量下降，外在 表现是发胖，也容易诱发心血管疾病。<br>骨骼肌率：人体有多个肌肉组成，其中骨胳肌是可以通过 锻炼增加的肌肉。
             * muscle_mass : 48.55
             * muscle_mass_1 : 38.5
             * muscle_mass_1_str : 偏低
             * muscle_mass_2 : 46.5
             * muscle_mass_2_str : 标准
             * muscle_mass_3_str : 充足
             * muscle_mass_str : 充足
             * muscle_mass_color : #1bc050
             * muscle_mass_desc : 您的肌肉比较发达，继续保持。
             * bone_mass : 2.56
             * bone_mass_1 : 3.0
             * bone_mass_1_str : 偏低
             * bone_mass_2 : 3.4
             * bone_mass_2_str : 标准
             * bone_mass_3_str : 偏高
             * bone_mass_str : 偏低
             * bone_mass_color : #0DC3D1
             * bone_mass_desc : 您的骨量水平偏低。长期低钙饮食、缺乏运动、过度减肥 等都可能引起骨量偏低，多吃含钙高的食物，多晒太阳， 多运动及时补钙。
             * ffm : 51.11
             * ffm_str : 标准
             * ffm_color : #1bc050
             * ffm_desc : 去脂体重是指除脂肪以外身体其他成分的重量，肌肉是其 中的主要部分。通过该指标可以看出您锻炼的效果，也可 以看出您减肥的潜力哦！
             * protein : 15.16
             * protein_1 : 16
             * protein_1_str : 偏低
             * protein_2 : 18
             * protein_2_str : 标准
             * protein_3_str : 充足
             * protein_str : 偏低
             * protein_color : #0DC3D1
             * protein_desc : 蛋白质不足会引起基础代谢减少，也会引起肌肉的数量减 少。坚持长期运动，适当提高肌肉比例，辅助于蛋白质的 补充，可以提升蛋白质比例。<br>蛋白质:生命的物质基础，是构成细胞的基本有机物。
             * subcutaneous_fat : 29.00
             * subcutaneous_fat_1 : 8.6
             * subcutaneous_fat_1_str : 偏低
             * subcutaneous_fat_2 : 16.7
             * subcutaneous_fat_2_str : 标准
             * subcutaneous_fat_3_str : 偏高
             * subcutaneous_fat_str : 偏高
             * subcutaneous_fat_color : #FFAD3E
             * subcutaneous_fat_desc : 皮下脂肪过多是外表肥胖的主要原因，除了有氧减脂以 外，多进行增肌训练，肌肉的增加可以让您拥有更完美的 体型。
             * visceral_fat_grade : 15
             * visceral_fat_grade_1 : 9
             * visceral_fat_grade_1_str : 标准
             * visceral_fat_grade_2 : 14
             * visceral_fat_grade_2_str : 偏高
             * visceral_fat_grade_3_str : 严重偏高
             * visceral_fat_grade_str : 严重偏高
             * visceral_fat_grade_color : #E13F14
             * visceral_fat_grade_desc : 内脏脂肪指数危险，罹患心脏病、高血压、高血脂和Ⅱ型 糖尿病风险大，您迫切需要控制体重、积极运动和限制饮 食。
             * body_age : 35
             * body_age_str : 不达标
             * body_age_color : #FFAD3E
             * body_age_desc : 您还有年轻空间，加油！
             * shape : 6.0
             * shape_str : 肥胖型
             * shape_color : #FFAD3E
             * shape_desc : 肥胖型
             * basal_metabolism : 1473
             * basal_metabolism_str : 不达标
             * basal_metabolism_color : #FFAD3E
             * basal_metabolism_desc : 您的标准基础代谢率为1844.4 kcal，目前处于未达标状态。 持续轻量运动能够提高身体的基础代谢率，而节食基础代 谢会大幅下降。
             * ideal_visual_weight : 45.9
             * ideal_visual_weight_str : 标准
             * ideal_visual_weight_color : #1bc050
             * ideal_visual_weight_desc : 根据您的身高和年龄，您的理想视觉体重是45.9 kg
             * fat_control : -14.22
             * fat_control_str : 不达标
             * fat_control_color : #FFAD3E
             * fat_control_desc : 根据健康的脂肪控制方式，建议您增加 /减少-14.22 kg脂肪。
             * inorganic_salt_status_str : 偏低
             * inorganic_salt_status_color : #0DC3D1
             * inorganic_salt_status_desc : 您的骨量水平偏低。长期低钙饮食、缺乏运动、过度减肥 等都可能引起骨量偏低，多吃含钙高的食物，多晒太阳， 多运动及时补钙。
             * muscle_ratio : 63.18
             * muscle_ratio_1 : 2958.72
             * muscle_ratio_1_str : 缺乏
             * muscle_ratio_2 : 3573.52
             * muscle_ratio_2_str : 正常
             * muscle_ratio_3_str : 充足
             * muscle_ratio_str : 缺乏
             * muscle_ratio_color : #0DC3D1
             * muscle_ratio_desc : 您的肌肉率为63.18% ，处于缺乏状态，拥有更多的肌肉能 让您展示更好体型。
             * muscle_control : 4.74
             * muscle_control_str : 不达标
             * muscle_control_color : #FFAD3E
             * muscle_control_desc : 根据健康的肌肉控制方式，建议您增加 /减少4.74  kg肌肉。
             * standard_weight : 49.70
             * standard_weight_str : 标准
             * standard_weight_color : #1bc050
             * standard_weight_desc : 根据您的身高和年龄，您的标准体重是49.70 。
             * water_content : 36.89
             * water_content_1 : 55
             * water_content_1_str : 偏低
             * water_content_2 : 65
             * water_content_2_str : 标准
             * water_content_3_str : 充足
             * water_content_str : 偏低
             * water_content_color : #0DC3D1
             * water_content_desc : 体水分率偏低，规律的饮食习惯和每天喝足8杯水可以维 持正常的体水分水平，充足的水分可以促进代谢，带走废 物和身体毒素。
             * weight_control : -9.48
             * weight_control_str : 不达标
             * weight_control_color : #FFAD3E
             * weight_control_desc : 根据健康的体重控制方式，建议您增加 /减少-9.48 kg体重。
             * obesity_1 : 15
             * obesity_1_str : 标准
             * obesity_2 : 32
             * obesity_2_str : 超重
             * obesity_3 : 52
             * obesity_3_str : 轻度肥胖
             * obesity_4 : 74
             * obesity_4_str : 中度肥胖
             * obesity_5_str : 严重肥胖
             * obesity : 54.63
             * obesity_str : 中度肥胖
             * obesity_color : #FFAD3E
             * obesity_desc : 判断是否肥胖的指标
             * protein_quality : 11.65
             * protein_quality_1 : 12.30
             * protein_quality_1_str : 偏低
             * protein_quality_2 : 13.83
             * protein_quality_2_str : 标准
             * protein_quality_3_str : 充足
             * protein_quality_str : 偏低
             * protein_quality_color : #0DC3D1
             * protein_quality_desc : 您缺少足够的蛋白质，需要加强营养，增加蛋白质。
             * fat_weight : 25.74
             * fat_weight_1 : 8.45
             * fat_weight_1_str : 偏低
             * fat_weight_2 : 16.14
             * fat_weight_2_str : 标准
             * fat_weight_3 : 19.98
             * fat_weight_3_str : 偏高
             * fat_weight_4_str : 严重偏高
             * fat_weight_str : 严重偏高
             * fat_weight_color : #E13F14
             * fat_weight_desc : 体内所含脂肪的重量。
             * bf_record : 58.30
             * bf_record_str : 58.30
             * bf_record_color : #FFAD3E
             * bf_record_desc : 您的身体状况简直不能忍...接受现实还是改变自己,做出 选择吧！！
             * birth : 1991-11-11
             * sex : 1
             * height : 151
             * add_date : 2019-11-27 15:50
             */

            private String weight;
            private String weight_1;
            private String weight_1_str;
            private String weight_2;
            private String weight_2_str;
            private String weight_3;
            private String weight_3_str;
            private String weight_4;
            private String weight_4_str;
            private String weight_5_str;
            private String weight_str;
            private String weight_color;
            private String weight_desc;
            private String bmi;
            private double bmi_1;
            private String bmi_1_str;
            private int bmi_2;
            private String bmi_2_str;
            private String bmi_3_str;
            private String bmi_str;
            private String bmi_color;
            private String bmi_desc;
            private String body_fat_per;
            private int body_fat_per_1;
            private String body_fat_per_1_str;
            private int body_fat_per_2;
            private String body_fat_per_2_str;
            private int body_fat_per_3;
            private String body_fat_per_3_str;
            private String body_fat_per_4_str;
            private String body_fat_per_str;
            private String body_fat_per_color;
            private String body_fat_per_desc;
            private String body_water_per;
            private int body_water_per_1;
            private String body_water_per_1_str;
            private int body_water_per_2;
            private String body_water_per_2_str;
            private String body_water_per_3_str;
            private String body_water_per_str;
            private String body_water_per_color;
            private String body_water_per_desc;
            private String skeletal_muscle_rate;
            private int skeletal_muscle_rate_1;
            private String skeletal_muscle_rate_1_str;
            private int skeletal_muscle_rate_2;
            private String skeletal_muscle_rate_2_str;
            private String skeletal_muscle_rate_3_str;
            private String skeletal_muscle_rate_str;
            private String skeletal_muscle_rate_color;
            private String skeletal_muscle_rate_desc;
            private String muscle_mass;
            private double muscle_mass_1;
            private String muscle_mass_1_str;
            private double muscle_mass_2;
            private String muscle_mass_2_str;
            private String muscle_mass_3_str;
            private String muscle_mass_str;
            private String muscle_mass_color;
            private String muscle_mass_desc;
            private String bone_mass;
            private String bone_mass_1;
            private String bone_mass_1_str;
            private String bone_mass_2;
            private String bone_mass_2_str;
            private String bone_mass_3_str;
            private String bone_mass_str;
            private String bone_mass_color;
            private String bone_mass_desc;
            private String ffm;
            private String ffm_str;
            private String ffm_color;
            private String ffm_desc;
            private String protein;
            private String protein_1;
            private String protein_1_str;
            private String protein_2;
            private String protein_2_str;
            private String protein_3_str;
            private String protein_str;
            private String protein_color;
            private String protein_desc;
            private String subcutaneous_fat;
            private String subcutaneous_fat_1;
            private String subcutaneous_fat_1_str;
            private String subcutaneous_fat_2;
            private String subcutaneous_fat_2_str;
            private String subcutaneous_fat_3_str;
            private String subcutaneous_fat_str;
            private String subcutaneous_fat_color;
            private String subcutaneous_fat_desc;
            private int visceral_fat_grade;
            private String visceral_fat_grade_1;
            private String visceral_fat_grade_1_str;
            private String visceral_fat_grade_2;
            private String visceral_fat_grade_2_str;
            private String visceral_fat_grade_3_str;
            private String visceral_fat_grade_str;
            private String visceral_fat_grade_color;
            private String visceral_fat_grade_desc;
            private int body_age;
            private String body_age_str;
            private String body_age_color;
            private String body_age_desc;
            private String shape;
            private String shape_str;
            private String shape_color;
            private String shape_desc;
            private int basal_metabolism;
            private String basal_metabolism_str;
            private String basal_metabolism_color;
            private String basal_metabolism_desc;
            private double ideal_visual_weight;
            private String ideal_visual_weight_str;
            private String ideal_visual_weight_color;
            private String ideal_visual_weight_desc;
            private String fat_control;
            private String fat_control_str;
            private String fat_control_color;
            private String fat_control_desc;
            private String inorganic_salt_status_str;
            private String inorganic_salt_status_color;
            private String inorganic_salt_status_desc;
            private String muscle_ratio;
            private String muscle_ratio_1;
            private String muscle_ratio_1_str;
            private String muscle_ratio_2;
            private String muscle_ratio_2_str;
            private String muscle_ratio_3_str;
            private String muscle_ratio_str;
            private String muscle_ratio_color;
            private String muscle_ratio_desc;
            private String muscle_control;
            private String muscle_control_str;
            private String muscle_control_color;
            private String muscle_control_desc;
            private String standard_weight;
            private String standard_weight_str;
            private String standard_weight_color;
            private String standard_weight_desc;
            private String water_content;
            private String water_content_1;
            private String water_content_1_str;
            private String water_content_2;
            private String water_content_2_str;
            private String water_content_3_str;
            private String water_content_str;
            private String water_content_color;
            private String water_content_desc;
            private String weight_control;
            private String weight_control_str;
            private String weight_control_color;
            private String weight_control_desc;
            private String obesity_1;
            private String obesity_1_str;
            private String obesity_2;
            private String obesity_2_str;
            private String obesity_3;
            private String obesity_3_str;
            private String obesity_4;
            private String obesity_4_str;
            private String obesity_5_str;
            private String obesity;
            private String obesity_str;
            private String obesity_color;
            private String obesity_desc;
            private String protein_quality;
            private String protein_quality_1;
            private String protein_quality_1_str;
            private String protein_quality_2;
            private String protein_quality_2_str;
            private String protein_quality_3_str;
            private String protein_quality_str;
            private String protein_quality_color;
            private String protein_quality_desc;
            private String fat_weight;
            private String fat_weight_1;
            private String fat_weight_1_str;
            private String fat_weight_2;
            private String fat_weight_2_str;
            private String fat_weight_3;
            private String fat_weight_3_str;
            private String fat_weight_4_str;
            private String fat_weight_str;
            private String fat_weight_color;
            private String fat_weight_desc;
            private String bf_record;
            private String bf_record_str;
            private String bf_record_color;
            private String bf_record_desc;
            private String birth;
            private int sex;
            private int height;
            private String add_date;

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getWeight_1() {
                return weight_1;
            }

            public void setWeight_1(String weight_1) {
                this.weight_1 = weight_1;
            }

            public String getWeight_1_str() {
                return weight_1_str;
            }

            public void setWeight_1_str(String weight_1_str) {
                this.weight_1_str = weight_1_str;
            }

            public String getWeight_2() {
                return weight_2;
            }

            public void setWeight_2(String weight_2) {
                this.weight_2 = weight_2;
            }

            public String getWeight_2_str() {
                return weight_2_str;
            }

            public void setWeight_2_str(String weight_2_str) {
                this.weight_2_str = weight_2_str;
            }

            public String getWeight_3() {
                return weight_3;
            }

            public void setWeight_3(String weight_3) {
                this.weight_3 = weight_3;
            }

            public String getWeight_3_str() {
                return weight_3_str;
            }

            public void setWeight_3_str(String weight_3_str) {
                this.weight_3_str = weight_3_str;
            }

            public String getWeight_4() {
                return weight_4;
            }

            public void setWeight_4(String weight_4) {
                this.weight_4 = weight_4;
            }

            public String getWeight_4_str() {
                return weight_4_str;
            }

            public void setWeight_4_str(String weight_4_str) {
                this.weight_4_str = weight_4_str;
            }

            public String getWeight_5_str() {
                return weight_5_str;
            }

            public void setWeight_5_str(String weight_5_str) {
                this.weight_5_str = weight_5_str;
            }

            public String getWeight_str() {
                return weight_str;
            }

            public void setWeight_str(String weight_str) {
                this.weight_str = weight_str;
            }

            public String getWeight_color() {
                return weight_color;
            }

            public void setWeight_color(String weight_color) {
                this.weight_color = weight_color;
            }

            public String getWeight_desc() {
                return weight_desc;
            }

            public void setWeight_desc(String weight_desc) {
                this.weight_desc = weight_desc;
            }

            public String getBmi() {
                return bmi;
            }

            public void setBmi(String bmi) {
                this.bmi = bmi;
            }

            public double getBmi_1() {
                return bmi_1;
            }

            public void setBmi_1(double bmi_1) {
                this.bmi_1 = bmi_1;
            }

            public String getBmi_1_str() {
                return bmi_1_str;
            }

            public void setBmi_1_str(String bmi_1_str) {
                this.bmi_1_str = bmi_1_str;
            }

            public int getBmi_2() {
                return bmi_2;
            }

            public void setBmi_2(int bmi_2) {
                this.bmi_2 = bmi_2;
            }

            public String getBmi_2_str() {
                return bmi_2_str;
            }

            public void setBmi_2_str(String bmi_2_str) {
                this.bmi_2_str = bmi_2_str;
            }

            public String getBmi_3_str() {
                return bmi_3_str;
            }

            public void setBmi_3_str(String bmi_3_str) {
                this.bmi_3_str = bmi_3_str;
            }

            public String getBmi_str() {
                return bmi_str;
            }

            public void setBmi_str(String bmi_str) {
                this.bmi_str = bmi_str;
            }

            public String getBmi_color() {
                return bmi_color;
            }

            public void setBmi_color(String bmi_color) {
                this.bmi_color = bmi_color;
            }

            public String getBmi_desc() {
                return bmi_desc;
            }

            public void setBmi_desc(String bmi_desc) {
                this.bmi_desc = bmi_desc;
            }

            public String getBody_fat_per() {
                return body_fat_per;
            }

            public void setBody_fat_per(String body_fat_per) {
                this.body_fat_per = body_fat_per;
            }

            public int getBody_fat_per_1() {
                return body_fat_per_1;
            }

            public void setBody_fat_per_1(int body_fat_per_1) {
                this.body_fat_per_1 = body_fat_per_1;
            }

            public String getBody_fat_per_1_str() {
                return body_fat_per_1_str;
            }

            public void setBody_fat_per_1_str(String body_fat_per_1_str) {
                this.body_fat_per_1_str = body_fat_per_1_str;
            }

            public int getBody_fat_per_2() {
                return body_fat_per_2;
            }

            public void setBody_fat_per_2(int body_fat_per_2) {
                this.body_fat_per_2 = body_fat_per_2;
            }

            public String getBody_fat_per_2_str() {
                return body_fat_per_2_str;
            }

            public void setBody_fat_per_2_str(String body_fat_per_2_str) {
                this.body_fat_per_2_str = body_fat_per_2_str;
            }

            public int getBody_fat_per_3() {
                return body_fat_per_3;
            }

            public void setBody_fat_per_3(int body_fat_per_3) {
                this.body_fat_per_3 = body_fat_per_3;
            }

            public String getBody_fat_per_3_str() {
                return body_fat_per_3_str;
            }

            public void setBody_fat_per_3_str(String body_fat_per_3_str) {
                this.body_fat_per_3_str = body_fat_per_3_str;
            }

            public String getBody_fat_per_4_str() {
                return body_fat_per_4_str;
            }

            public void setBody_fat_per_4_str(String body_fat_per_4_str) {
                this.body_fat_per_4_str = body_fat_per_4_str;
            }

            public String getBody_fat_per_str() {
                return body_fat_per_str;
            }

            public void setBody_fat_per_str(String body_fat_per_str) {
                this.body_fat_per_str = body_fat_per_str;
            }

            public String getBody_fat_per_color() {
                return body_fat_per_color;
            }

            public void setBody_fat_per_color(String body_fat_per_color) {
                this.body_fat_per_color = body_fat_per_color;
            }

            public String getBody_fat_per_desc() {
                return body_fat_per_desc;
            }

            public void setBody_fat_per_desc(String body_fat_per_desc) {
                this.body_fat_per_desc = body_fat_per_desc;
            }

            public String getBody_water_per() {
                return body_water_per;
            }

            public void setBody_water_per(String body_water_per) {
                this.body_water_per = body_water_per;
            }

            public int getBody_water_per_1() {
                return body_water_per_1;
            }

            public void setBody_water_per_1(int body_water_per_1) {
                this.body_water_per_1 = body_water_per_1;
            }

            public String getBody_water_per_1_str() {
                return body_water_per_1_str;
            }

            public void setBody_water_per_1_str(String body_water_per_1_str) {
                this.body_water_per_1_str = body_water_per_1_str;
            }

            public int getBody_water_per_2() {
                return body_water_per_2;
            }

            public void setBody_water_per_2(int body_water_per_2) {
                this.body_water_per_2 = body_water_per_2;
            }

            public String getBody_water_per_2_str() {
                return body_water_per_2_str;
            }

            public void setBody_water_per_2_str(String body_water_per_2_str) {
                this.body_water_per_2_str = body_water_per_2_str;
            }

            public String getBody_water_per_3_str() {
                return body_water_per_3_str;
            }

            public void setBody_water_per_3_str(String body_water_per_3_str) {
                this.body_water_per_3_str = body_water_per_3_str;
            }

            public String getBody_water_per_str() {
                return body_water_per_str;
            }

            public void setBody_water_per_str(String body_water_per_str) {
                this.body_water_per_str = body_water_per_str;
            }

            public String getBody_water_per_color() {
                return body_water_per_color;
            }

            public void setBody_water_per_color(String body_water_per_color) {
                this.body_water_per_color = body_water_per_color;
            }

            public String getBody_water_per_desc() {
                return body_water_per_desc;
            }

            public void setBody_water_per_desc(String body_water_per_desc) {
                this.body_water_per_desc = body_water_per_desc;
            }

            public String getSkeletal_muscle_rate() {
                return skeletal_muscle_rate;
            }

            public void setSkeletal_muscle_rate(String skeletal_muscle_rate) {
                this.skeletal_muscle_rate = skeletal_muscle_rate;
            }

            public int getSkeletal_muscle_rate_1() {
                return skeletal_muscle_rate_1;
            }

            public void setSkeletal_muscle_rate_1(int skeletal_muscle_rate_1) {
                this.skeletal_muscle_rate_1 = skeletal_muscle_rate_1;
            }

            public String getSkeletal_muscle_rate_1_str() {
                return skeletal_muscle_rate_1_str;
            }

            public void setSkeletal_muscle_rate_1_str(String skeletal_muscle_rate_1_str) {
                this.skeletal_muscle_rate_1_str = skeletal_muscle_rate_1_str;
            }

            public int getSkeletal_muscle_rate_2() {
                return skeletal_muscle_rate_2;
            }

            public void setSkeletal_muscle_rate_2(int skeletal_muscle_rate_2) {
                this.skeletal_muscle_rate_2 = skeletal_muscle_rate_2;
            }

            public String getSkeletal_muscle_rate_2_str() {
                return skeletal_muscle_rate_2_str;
            }

            public void setSkeletal_muscle_rate_2_str(String skeletal_muscle_rate_2_str) {
                this.skeletal_muscle_rate_2_str = skeletal_muscle_rate_2_str;
            }

            public String getSkeletal_muscle_rate_3_str() {
                return skeletal_muscle_rate_3_str;
            }

            public void setSkeletal_muscle_rate_3_str(String skeletal_muscle_rate_3_str) {
                this.skeletal_muscle_rate_3_str = skeletal_muscle_rate_3_str;
            }

            public String getSkeletal_muscle_rate_str() {
                return skeletal_muscle_rate_str;
            }

            public void setSkeletal_muscle_rate_str(String skeletal_muscle_rate_str) {
                this.skeletal_muscle_rate_str = skeletal_muscle_rate_str;
            }

            public String getSkeletal_muscle_rate_color() {
                return skeletal_muscle_rate_color;
            }

            public void setSkeletal_muscle_rate_color(String skeletal_muscle_rate_color) {
                this.skeletal_muscle_rate_color = skeletal_muscle_rate_color;
            }

            public String getSkeletal_muscle_rate_desc() {
                return skeletal_muscle_rate_desc;
            }

            public void setSkeletal_muscle_rate_desc(String skeletal_muscle_rate_desc) {
                this.skeletal_muscle_rate_desc = skeletal_muscle_rate_desc;
            }

            public String getMuscle_mass() {
                return muscle_mass;
            }

            public void setMuscle_mass(String muscle_mass) {
                this.muscle_mass = muscle_mass;
            }

            public double getMuscle_mass_1() {
                return muscle_mass_1;
            }

            public void setMuscle_mass_1(double muscle_mass_1) {
                this.muscle_mass_1 = muscle_mass_1;
            }

            public String getMuscle_mass_1_str() {
                return muscle_mass_1_str;
            }

            public void setMuscle_mass_1_str(String muscle_mass_1_str) {
                this.muscle_mass_1_str = muscle_mass_1_str;
            }

            public double getMuscle_mass_2() {
                return muscle_mass_2;
            }

            public void setMuscle_mass_2(double muscle_mass_2) {
                this.muscle_mass_2 = muscle_mass_2;
            }

            public String getMuscle_mass_2_str() {
                return muscle_mass_2_str;
            }

            public void setMuscle_mass_2_str(String muscle_mass_2_str) {
                this.muscle_mass_2_str = muscle_mass_2_str;
            }

            public String getMuscle_mass_3_str() {
                return muscle_mass_3_str;
            }

            public void setMuscle_mass_3_str(String muscle_mass_3_str) {
                this.muscle_mass_3_str = muscle_mass_3_str;
            }

            public String getMuscle_mass_str() {
                return muscle_mass_str;
            }

            public void setMuscle_mass_str(String muscle_mass_str) {
                this.muscle_mass_str = muscle_mass_str;
            }

            public String getMuscle_mass_color() {
                return muscle_mass_color;
            }

            public void setMuscle_mass_color(String muscle_mass_color) {
                this.muscle_mass_color = muscle_mass_color;
            }

            public String getMuscle_mass_desc() {
                return muscle_mass_desc;
            }

            public void setMuscle_mass_desc(String muscle_mass_desc) {
                this.muscle_mass_desc = muscle_mass_desc;
            }

            public String getBone_mass() {
                return bone_mass;
            }

            public void setBone_mass(String bone_mass) {
                this.bone_mass = bone_mass;
            }

            public String getBone_mass_1() {
                return bone_mass_1;
            }

            public void setBone_mass_1(String bone_mass_1) {
                this.bone_mass_1 = bone_mass_1;
            }

            public String getBone_mass_1_str() {
                return bone_mass_1_str;
            }

            public void setBone_mass_1_str(String bone_mass_1_str) {
                this.bone_mass_1_str = bone_mass_1_str;
            }

            public String getBone_mass_2() {
                return bone_mass_2;
            }

            public void setBone_mass_2(String bone_mass_2) {
                this.bone_mass_2 = bone_mass_2;
            }

            public String getBone_mass_2_str() {
                return bone_mass_2_str;
            }

            public void setBone_mass_2_str(String bone_mass_2_str) {
                this.bone_mass_2_str = bone_mass_2_str;
            }

            public String getBone_mass_3_str() {
                return bone_mass_3_str;
            }

            public void setBone_mass_3_str(String bone_mass_3_str) {
                this.bone_mass_3_str = bone_mass_3_str;
            }

            public String getBone_mass_str() {
                return bone_mass_str;
            }

            public void setBone_mass_str(String bone_mass_str) {
                this.bone_mass_str = bone_mass_str;
            }

            public String getBone_mass_color() {
                return bone_mass_color;
            }

            public void setBone_mass_color(String bone_mass_color) {
                this.bone_mass_color = bone_mass_color;
            }

            public String getBone_mass_desc() {
                return bone_mass_desc;
            }

            public void setBone_mass_desc(String bone_mass_desc) {
                this.bone_mass_desc = bone_mass_desc;
            }

            public String getFfm() {
                return ffm;
            }

            public void setFfm(String ffm) {
                this.ffm = ffm;
            }

            public String getFfm_str() {
                return ffm_str;
            }

            public void setFfm_str(String ffm_str) {
                this.ffm_str = ffm_str;
            }

            public String getFfm_color() {
                return ffm_color;
            }

            public void setFfm_color(String ffm_color) {
                this.ffm_color = ffm_color;
            }

            public String getFfm_desc() {
                return ffm_desc;
            }

            public void setFfm_desc(String ffm_desc) {
                this.ffm_desc = ffm_desc;
            }

            public String getProtein() {
                return protein;
            }

            public void setProtein(String protein) {
                this.protein = protein;
            }

            public String getProtein_1() {
                return protein_1;
            }

            public void setProtein_1(String protein_1) {
                this.protein_1 = protein_1;
            }

            public String getProtein_1_str() {
                return protein_1_str;
            }

            public void setProtein_1_str(String protein_1_str) {
                this.protein_1_str = protein_1_str;
            }

            public String getProtein_2() {
                return protein_2;
            }

            public void setProtein_2(String protein_2) {
                this.protein_2 = protein_2;
            }

            public String getProtein_2_str() {
                return protein_2_str;
            }

            public void setProtein_2_str(String protein_2_str) {
                this.protein_2_str = protein_2_str;
            }

            public String getProtein_3_str() {
                return protein_3_str;
            }

            public void setProtein_3_str(String protein_3_str) {
                this.protein_3_str = protein_3_str;
            }

            public String getProtein_str() {
                return protein_str;
            }

            public void setProtein_str(String protein_str) {
                this.protein_str = protein_str;
            }

            public String getProtein_color() {
                return protein_color;
            }

            public void setProtein_color(String protein_color) {
                this.protein_color = protein_color;
            }

            public String getProtein_desc() {
                return protein_desc;
            }

            public void setProtein_desc(String protein_desc) {
                this.protein_desc = protein_desc;
            }

            public String getSubcutaneous_fat() {
                return subcutaneous_fat;
            }

            public void setSubcutaneous_fat(String subcutaneous_fat) {
                this.subcutaneous_fat = subcutaneous_fat;
            }

            public String getSubcutaneous_fat_1() {
                return subcutaneous_fat_1;
            }

            public void setSubcutaneous_fat_1(String subcutaneous_fat_1) {
                this.subcutaneous_fat_1 = subcutaneous_fat_1;
            }

            public String getSubcutaneous_fat_1_str() {
                return subcutaneous_fat_1_str;
            }

            public void setSubcutaneous_fat_1_str(String subcutaneous_fat_1_str) {
                this.subcutaneous_fat_1_str = subcutaneous_fat_1_str;
            }

            public String getSubcutaneous_fat_2() {
                return subcutaneous_fat_2;
            }

            public void setSubcutaneous_fat_2(String subcutaneous_fat_2) {
                this.subcutaneous_fat_2 = subcutaneous_fat_2;
            }

            public String getSubcutaneous_fat_2_str() {
                return subcutaneous_fat_2_str;
            }

            public void setSubcutaneous_fat_2_str(String subcutaneous_fat_2_str) {
                this.subcutaneous_fat_2_str = subcutaneous_fat_2_str;
            }

            public String getSubcutaneous_fat_3_str() {
                return subcutaneous_fat_3_str;
            }

            public void setSubcutaneous_fat_3_str(String subcutaneous_fat_3_str) {
                this.subcutaneous_fat_3_str = subcutaneous_fat_3_str;
            }

            public String getSubcutaneous_fat_str() {
                return subcutaneous_fat_str;
            }

            public void setSubcutaneous_fat_str(String subcutaneous_fat_str) {
                this.subcutaneous_fat_str = subcutaneous_fat_str;
            }

            public String getSubcutaneous_fat_color() {
                return subcutaneous_fat_color;
            }

            public void setSubcutaneous_fat_color(String subcutaneous_fat_color) {
                this.subcutaneous_fat_color = subcutaneous_fat_color;
            }

            public String getSubcutaneous_fat_desc() {
                return subcutaneous_fat_desc;
            }

            public void setSubcutaneous_fat_desc(String subcutaneous_fat_desc) {
                this.subcutaneous_fat_desc = subcutaneous_fat_desc;
            }

            public int getVisceral_fat_grade() {
                return visceral_fat_grade;
            }

            public void setVisceral_fat_grade(int visceral_fat_grade) {
                this.visceral_fat_grade = visceral_fat_grade;
            }

            public String getVisceral_fat_grade_1() {
                return visceral_fat_grade_1;
            }

            public void setVisceral_fat_grade_1(String visceral_fat_grade_1) {
                this.visceral_fat_grade_1 = visceral_fat_grade_1;
            }

            public String getVisceral_fat_grade_1_str() {
                return visceral_fat_grade_1_str;
            }

            public void setVisceral_fat_grade_1_str(String visceral_fat_grade_1_str) {
                this.visceral_fat_grade_1_str = visceral_fat_grade_1_str;
            }

            public String getVisceral_fat_grade_2() {
                return visceral_fat_grade_2;
            }

            public void setVisceral_fat_grade_2(String visceral_fat_grade_2) {
                this.visceral_fat_grade_2 = visceral_fat_grade_2;
            }

            public String getVisceral_fat_grade_2_str() {
                return visceral_fat_grade_2_str;
            }

            public void setVisceral_fat_grade_2_str(String visceral_fat_grade_2_str) {
                this.visceral_fat_grade_2_str = visceral_fat_grade_2_str;
            }

            public String getVisceral_fat_grade_3_str() {
                return visceral_fat_grade_3_str;
            }

            public void setVisceral_fat_grade_3_str(String visceral_fat_grade_3_str) {
                this.visceral_fat_grade_3_str = visceral_fat_grade_3_str;
            }

            public String getVisceral_fat_grade_str() {
                return visceral_fat_grade_str;
            }

            public void setVisceral_fat_grade_str(String visceral_fat_grade_str) {
                this.visceral_fat_grade_str = visceral_fat_grade_str;
            }

            public String getVisceral_fat_grade_color() {
                return visceral_fat_grade_color;
            }

            public void setVisceral_fat_grade_color(String visceral_fat_grade_color) {
                this.visceral_fat_grade_color = visceral_fat_grade_color;
            }

            public String getVisceral_fat_grade_desc() {
                return visceral_fat_grade_desc;
            }

            public void setVisceral_fat_grade_desc(String visceral_fat_grade_desc) {
                this.visceral_fat_grade_desc = visceral_fat_grade_desc;
            }

            public int getBody_age() {
                return body_age;
            }

            public void setBody_age(int body_age) {
                this.body_age = body_age;
            }

            public String getBody_age_str() {
                return body_age_str;
            }

            public void setBody_age_str(String body_age_str) {
                this.body_age_str = body_age_str;
            }

            public String getBody_age_color() {
                return body_age_color;
            }

            public void setBody_age_color(String body_age_color) {
                this.body_age_color = body_age_color;
            }

            public String getBody_age_desc() {
                return body_age_desc;
            }

            public void setBody_age_desc(String body_age_desc) {
                this.body_age_desc = body_age_desc;
            }

            public String getShape() {
                return shape;
            }

            public void setShape(String shape) {
                this.shape = shape;
            }

            public String getShape_str() {
                return shape_str;
            }

            public void setShape_str(String shape_str) {
                this.shape_str = shape_str;
            }

            public String getShape_color() {
                return shape_color;
            }

            public void setShape_color(String shape_color) {
                this.shape_color = shape_color;
            }

            public String getShape_desc() {
                return shape_desc;
            }

            public void setShape_desc(String shape_desc) {
                this.shape_desc = shape_desc;
            }

            public int getBasal_metabolism() {
                return basal_metabolism;
            }

            public void setBasal_metabolism(int basal_metabolism) {
                this.basal_metabolism = basal_metabolism;
            }

            public String getBasal_metabolism_str() {
                return basal_metabolism_str;
            }

            public void setBasal_metabolism_str(String basal_metabolism_str) {
                this.basal_metabolism_str = basal_metabolism_str;
            }

            public String getBasal_metabolism_color() {
                return basal_metabolism_color;
            }

            public void setBasal_metabolism_color(String basal_metabolism_color) {
                this.basal_metabolism_color = basal_metabolism_color;
            }

            public String getBasal_metabolism_desc() {
                return basal_metabolism_desc;
            }

            public void setBasal_metabolism_desc(String basal_metabolism_desc) {
                this.basal_metabolism_desc = basal_metabolism_desc;
            }

            public double getIdeal_visual_weight() {
                return ideal_visual_weight;
            }

            public void setIdeal_visual_weight(double ideal_visual_weight) {
                this.ideal_visual_weight = ideal_visual_weight;
            }

            public String getIdeal_visual_weight_str() {
                return ideal_visual_weight_str;
            }

            public void setIdeal_visual_weight_str(String ideal_visual_weight_str) {
                this.ideal_visual_weight_str = ideal_visual_weight_str;
            }

            public String getIdeal_visual_weight_color() {
                return ideal_visual_weight_color;
            }

            public void setIdeal_visual_weight_color(String ideal_visual_weight_color) {
                this.ideal_visual_weight_color = ideal_visual_weight_color;
            }

            public String getIdeal_visual_weight_desc() {
                return ideal_visual_weight_desc;
            }

            public void setIdeal_visual_weight_desc(String ideal_visual_weight_desc) {
                this.ideal_visual_weight_desc = ideal_visual_weight_desc;
            }

            public String getFat_control() {
                return fat_control;
            }

            public void setFat_control(String fat_control) {
                this.fat_control = fat_control;
            }

            public String getFat_control_str() {
                return fat_control_str;
            }

            public void setFat_control_str(String fat_control_str) {
                this.fat_control_str = fat_control_str;
            }

            public String getFat_control_color() {
                return fat_control_color;
            }

            public void setFat_control_color(String fat_control_color) {
                this.fat_control_color = fat_control_color;
            }

            public String getFat_control_desc() {
                return fat_control_desc;
            }

            public void setFat_control_desc(String fat_control_desc) {
                this.fat_control_desc = fat_control_desc;
            }

            public String getInorganic_salt_status_str() {
                return inorganic_salt_status_str;
            }

            public void setInorganic_salt_status_str(String inorganic_salt_status_str) {
                this.inorganic_salt_status_str = inorganic_salt_status_str;
            }

            public String getInorganic_salt_status_color() {
                return inorganic_salt_status_color;
            }

            public void setInorganic_salt_status_color(String inorganic_salt_status_color) {
                this.inorganic_salt_status_color = inorganic_salt_status_color;
            }

            public String getInorganic_salt_status_desc() {
                return inorganic_salt_status_desc;
            }

            public void setInorganic_salt_status_desc(String inorganic_salt_status_desc) {
                this.inorganic_salt_status_desc = inorganic_salt_status_desc;
            }

            public String getMuscle_ratio() {
                return muscle_ratio;
            }

            public void setMuscle_ratio(String muscle_ratio) {
                this.muscle_ratio = muscle_ratio;
            }

            public String getMuscle_ratio_1() {
                return muscle_ratio_1;
            }

            public void setMuscle_ratio_1(String muscle_ratio_1) {
                this.muscle_ratio_1 = muscle_ratio_1;
            }

            public String getMuscle_ratio_1_str() {
                return muscle_ratio_1_str;
            }

            public void setMuscle_ratio_1_str(String muscle_ratio_1_str) {
                this.muscle_ratio_1_str = muscle_ratio_1_str;
            }

            public String getMuscle_ratio_2() {
                return muscle_ratio_2;
            }

            public void setMuscle_ratio_2(String muscle_ratio_2) {
                this.muscle_ratio_2 = muscle_ratio_2;
            }

            public String getMuscle_ratio_2_str() {
                return muscle_ratio_2_str;
            }

            public void setMuscle_ratio_2_str(String muscle_ratio_2_str) {
                this.muscle_ratio_2_str = muscle_ratio_2_str;
            }

            public String getMuscle_ratio_3_str() {
                return muscle_ratio_3_str;
            }

            public void setMuscle_ratio_3_str(String muscle_ratio_3_str) {
                this.muscle_ratio_3_str = muscle_ratio_3_str;
            }

            public String getMuscle_ratio_str() {
                return muscle_ratio_str;
            }

            public void setMuscle_ratio_str(String muscle_ratio_str) {
                this.muscle_ratio_str = muscle_ratio_str;
            }

            public String getMuscle_ratio_color() {
                return muscle_ratio_color;
            }

            public void setMuscle_ratio_color(String muscle_ratio_color) {
                this.muscle_ratio_color = muscle_ratio_color;
            }

            public String getMuscle_ratio_desc() {
                return muscle_ratio_desc;
            }

            public void setMuscle_ratio_desc(String muscle_ratio_desc) {
                this.muscle_ratio_desc = muscle_ratio_desc;
            }

            public String getMuscle_control() {
                return muscle_control;
            }

            public void setMuscle_control(String muscle_control) {
                this.muscle_control = muscle_control;
            }

            public String getMuscle_control_str() {
                return muscle_control_str;
            }

            public void setMuscle_control_str(String muscle_control_str) {
                this.muscle_control_str = muscle_control_str;
            }

            public String getMuscle_control_color() {
                return muscle_control_color;
            }

            public void setMuscle_control_color(String muscle_control_color) {
                this.muscle_control_color = muscle_control_color;
            }

            public String getMuscle_control_desc() {
                return muscle_control_desc;
            }

            public void setMuscle_control_desc(String muscle_control_desc) {
                this.muscle_control_desc = muscle_control_desc;
            }

            public String getStandard_weight() {
                return standard_weight;
            }

            public void setStandard_weight(String standard_weight) {
                this.standard_weight = standard_weight;
            }

            public String getStandard_weight_str() {
                return standard_weight_str;
            }

            public void setStandard_weight_str(String standard_weight_str) {
                this.standard_weight_str = standard_weight_str;
            }

            public String getStandard_weight_color() {
                return standard_weight_color;
            }

            public void setStandard_weight_color(String standard_weight_color) {
                this.standard_weight_color = standard_weight_color;
            }

            public String getStandard_weight_desc() {
                return standard_weight_desc;
            }

            public void setStandard_weight_desc(String standard_weight_desc) {
                this.standard_weight_desc = standard_weight_desc;
            }

            public String getWater_content() {
                return water_content;
            }

            public void setWater_content(String water_content) {
                this.water_content = water_content;
            }

            public String getWater_content_1() {
                return water_content_1;
            }

            public void setWater_content_1(String water_content_1) {
                this.water_content_1 = water_content_1;
            }

            public String getWater_content_1_str() {
                return water_content_1_str;
            }

            public void setWater_content_1_str(String water_content_1_str) {
                this.water_content_1_str = water_content_1_str;
            }

            public String getWater_content_2() {
                return water_content_2;
            }

            public void setWater_content_2(String water_content_2) {
                this.water_content_2 = water_content_2;
            }

            public String getWater_content_2_str() {
                return water_content_2_str;
            }

            public void setWater_content_2_str(String water_content_2_str) {
                this.water_content_2_str = water_content_2_str;
            }

            public String getWater_content_3_str() {
                return water_content_3_str;
            }

            public void setWater_content_3_str(String water_content_3_str) {
                this.water_content_3_str = water_content_3_str;
            }

            public String getWater_content_str() {
                return water_content_str;
            }

            public void setWater_content_str(String water_content_str) {
                this.water_content_str = water_content_str;
            }

            public String getWater_content_color() {
                return water_content_color;
            }

            public void setWater_content_color(String water_content_color) {
                this.water_content_color = water_content_color;
            }

            public String getWater_content_desc() {
                return water_content_desc;
            }

            public void setWater_content_desc(String water_content_desc) {
                this.water_content_desc = water_content_desc;
            }

            public String getWeight_control() {
                return weight_control;
            }

            public void setWeight_control(String weight_control) {
                this.weight_control = weight_control;
            }

            public String getWeight_control_str() {
                return weight_control_str;
            }

            public void setWeight_control_str(String weight_control_str) {
                this.weight_control_str = weight_control_str;
            }

            public String getWeight_control_color() {
                return weight_control_color;
            }

            public void setWeight_control_color(String weight_control_color) {
                this.weight_control_color = weight_control_color;
            }

            public String getWeight_control_desc() {
                return weight_control_desc;
            }

            public void setWeight_control_desc(String weight_control_desc) {
                this.weight_control_desc = weight_control_desc;
            }

            public String getObesity_1() {
                return obesity_1;
            }

            public void setObesity_1(String obesity_1) {
                this.obesity_1 = obesity_1;
            }

            public String getObesity_1_str() {
                return obesity_1_str;
            }

            public void setObesity_1_str(String obesity_1_str) {
                this.obesity_1_str = obesity_1_str;
            }

            public String getObesity_2() {
                return obesity_2;
            }

            public void setObesity_2(String obesity_2) {
                this.obesity_2 = obesity_2;
            }

            public String getObesity_2_str() {
                return obesity_2_str;
            }

            public void setObesity_2_str(String obesity_2_str) {
                this.obesity_2_str = obesity_2_str;
            }

            public String getObesity_3() {
                return obesity_3;
            }

            public void setObesity_3(String obesity_3) {
                this.obesity_3 = obesity_3;
            }

            public String getObesity_3_str() {
                return obesity_3_str;
            }

            public void setObesity_3_str(String obesity_3_str) {
                this.obesity_3_str = obesity_3_str;
            }

            public String getObesity_4() {
                return obesity_4;
            }

            public void setObesity_4(String obesity_4) {
                this.obesity_4 = obesity_4;
            }

            public String getObesity_4_str() {
                return obesity_4_str;
            }

            public void setObesity_4_str(String obesity_4_str) {
                this.obesity_4_str = obesity_4_str;
            }

            public String getObesity_5_str() {
                return obesity_5_str;
            }

            public void setObesity_5_str(String obesity_5_str) {
                this.obesity_5_str = obesity_5_str;
            }

            public String getObesity() {
                return obesity;
            }

            public void setObesity(String obesity) {
                this.obesity = obesity;
            }

            public String getObesity_str() {
                return obesity_str;
            }

            public void setObesity_str(String obesity_str) {
                this.obesity_str = obesity_str;
            }

            public String getObesity_color() {
                return obesity_color;
            }

            public void setObesity_color(String obesity_color) {
                this.obesity_color = obesity_color;
            }

            public String getObesity_desc() {
                return obesity_desc;
            }

            public void setObesity_desc(String obesity_desc) {
                this.obesity_desc = obesity_desc;
            }

            public String getProtein_quality() {
                return protein_quality;
            }

            public void setProtein_quality(String protein_quality) {
                this.protein_quality = protein_quality;
            }

            public String getProtein_quality_1() {
                return protein_quality_1;
            }

            public void setProtein_quality_1(String protein_quality_1) {
                this.protein_quality_1 = protein_quality_1;
            }

            public String getProtein_quality_1_str() {
                return protein_quality_1_str;
            }

            public void setProtein_quality_1_str(String protein_quality_1_str) {
                this.protein_quality_1_str = protein_quality_1_str;
            }

            public String getProtein_quality_2() {
                return protein_quality_2;
            }

            public void setProtein_quality_2(String protein_quality_2) {
                this.protein_quality_2 = protein_quality_2;
            }

            public String getProtein_quality_2_str() {
                return protein_quality_2_str;
            }

            public void setProtein_quality_2_str(String protein_quality_2_str) {
                this.protein_quality_2_str = protein_quality_2_str;
            }

            public String getProtein_quality_3_str() {
                return protein_quality_3_str;
            }

            public void setProtein_quality_3_str(String protein_quality_3_str) {
                this.protein_quality_3_str = protein_quality_3_str;
            }

            public String getProtein_quality_str() {
                return protein_quality_str;
            }

            public void setProtein_quality_str(String protein_quality_str) {
                this.protein_quality_str = protein_quality_str;
            }

            public String getProtein_quality_color() {
                return protein_quality_color;
            }

            public void setProtein_quality_color(String protein_quality_color) {
                this.protein_quality_color = protein_quality_color;
            }

            public String getProtein_quality_desc() {
                return protein_quality_desc;
            }

            public void setProtein_quality_desc(String protein_quality_desc) {
                this.protein_quality_desc = protein_quality_desc;
            }

            public String getFat_weight() {
                return fat_weight;
            }

            public void setFat_weight(String fat_weight) {
                this.fat_weight = fat_weight;
            }

            public String getFat_weight_1() {
                return fat_weight_1;
            }

            public void setFat_weight_1(String fat_weight_1) {
                this.fat_weight_1 = fat_weight_1;
            }

            public String getFat_weight_1_str() {
                return fat_weight_1_str;
            }

            public void setFat_weight_1_str(String fat_weight_1_str) {
                this.fat_weight_1_str = fat_weight_1_str;
            }

            public String getFat_weight_2() {
                return fat_weight_2;
            }

            public void setFat_weight_2(String fat_weight_2) {
                this.fat_weight_2 = fat_weight_2;
            }

            public String getFat_weight_2_str() {
                return fat_weight_2_str;
            }

            public void setFat_weight_2_str(String fat_weight_2_str) {
                this.fat_weight_2_str = fat_weight_2_str;
            }

            public String getFat_weight_3() {
                return fat_weight_3;
            }

            public void setFat_weight_3(String fat_weight_3) {
                this.fat_weight_3 = fat_weight_3;
            }

            public String getFat_weight_3_str() {
                return fat_weight_3_str;
            }

            public void setFat_weight_3_str(String fat_weight_3_str) {
                this.fat_weight_3_str = fat_weight_3_str;
            }

            public String getFat_weight_4_str() {
                return fat_weight_4_str;
            }

            public void setFat_weight_4_str(String fat_weight_4_str) {
                this.fat_weight_4_str = fat_weight_4_str;
            }

            public String getFat_weight_str() {
                return fat_weight_str;
            }

            public void setFat_weight_str(String fat_weight_str) {
                this.fat_weight_str = fat_weight_str;
            }

            public String getFat_weight_color() {
                return fat_weight_color;
            }

            public void setFat_weight_color(String fat_weight_color) {
                this.fat_weight_color = fat_weight_color;
            }

            public String getFat_weight_desc() {
                return fat_weight_desc;
            }

            public void setFat_weight_desc(String fat_weight_desc) {
                this.fat_weight_desc = fat_weight_desc;
            }

            public String getBf_record() {
                return bf_record;
            }

            public void setBf_record(String bf_record) {
                this.bf_record = bf_record;
            }

            public String getBf_record_str() {
                return bf_record_str;
            }

            public void setBf_record_str(String bf_record_str) {
                this.bf_record_str = bf_record_str;
            }

            public String getBf_record_color() {
                return bf_record_color;
            }

            public void setBf_record_color(String bf_record_color) {
                this.bf_record_color = bf_record_color;
            }

            public String getBf_record_desc() {
                return bf_record_desc;
            }

            public void setBf_record_desc(String bf_record_desc) {
                this.bf_record_desc = bf_record_desc;
            }

            public String getBirth() {
                return birth;
            }

            public void setBirth(String birth) {
                this.birth = birth;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }
        }
    }
}
